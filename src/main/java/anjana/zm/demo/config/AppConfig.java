package anjana.zm.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ComponentScan(basePackages = "anjana.zm.demo.thread")
public class AppConfig {

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(1);
		pool.setMaxPoolSize(1);
		pool.setQueueCapacity(2);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}

}