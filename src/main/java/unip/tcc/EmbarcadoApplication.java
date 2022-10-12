package unip.tcc;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import unip.tcc.service.ComunicacaoSerial;

@SpringBootApplication
@EnableAsync
public class EmbarcadoApplication {
	
	@Autowired ComunicacaoSerial mainClass;

	public static void main(String[] args) {
		 ApplicationContext context = new SpringApplicationBuilder(EmbarcadoApplication.class)
				    .headless(false).run(args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void handleContextStart() {
		mainClass.start();
	}
	
	@Bean
	public Executor asyncExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(2);
	    executor.setMaxPoolSize(2);
	    executor.setQueueCapacity(500);
	    executor.setThreadNamePrefix("JDAsync-");
	    executor.initialize();
	    return executor;
	}

}
