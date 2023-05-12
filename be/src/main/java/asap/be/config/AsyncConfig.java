package asap.be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AsyncConfig extends AsyncConfigurerSupport {


    @Bean(name="threadPoolTaskExecutor - Mail")
    public Executor mailAsyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(10); //동시에 동작하는 최대 스레드 개수
        executor.setCorePoolSize(5); // 대기중인 스레드 수
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("mail_async-");
        executor.initialize();

        return executor;
    }
}
