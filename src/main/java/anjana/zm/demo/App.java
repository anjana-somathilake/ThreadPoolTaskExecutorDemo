package anjana.zm.demo;

import anjana.zm.demo.components.PrintTask;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import static java.lang.System.out;

public class App {
	public static void main(String[] args) {


		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Config.xml");


		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context
				.getBean("taskExecutor");


		PrintTask printTask1 = (PrintTask) context.getBean("printTask");
		printTask1.setName("Thread 1");
		out.println("task1 is added");
		taskExecutor.execute(printTask1);


		PrintTask printTask2 = (PrintTask) context.getBean("printTask");
		printTask2.setName("Thread 2");
		out.println("task2 is added");
		taskExecutor.execute(printTask2);

		PrintTask printTask3 = (PrintTask) context.getBean("printTask");
		printTask3.setName("Thread 3");
		out.println("task3 is added");
		taskExecutor.execute(printTask3);

		for (;;) {
			int count = taskExecutor.getActiveCount();
			out.println("Active Threads : " + count + " PoolSize : " + taskExecutor.getPoolSize() );

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (count == 0) {
				taskExecutor.shutdown();
				break;
			}
		}
		 

	}
}
