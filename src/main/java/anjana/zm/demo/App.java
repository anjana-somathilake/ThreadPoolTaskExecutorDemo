package anjana.zm.demo;

import anjana.zm.demo.config.AppConfig;
import anjana.zm.demo.thread.PrintTask;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import static java.lang.System.out;

public class App {
	public static void main(String[] args) {

		
		ApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context
				.getBean("taskExecutor");


		PrintTask printTask1 = (PrintTask) context.getBean("printTask");
		printTask1.setName("Thread 1");
		taskExecutor.execute(printTask1);


		PrintTask printTask2 = (PrintTask) context.getBean("printTask");
		printTask2.setName("Thread 2");
		taskExecutor.execute(printTask2);

		PrintTask printTask3 = (PrintTask) context.getBean("printTask");
		printTask3.setName("Thread 3");
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
