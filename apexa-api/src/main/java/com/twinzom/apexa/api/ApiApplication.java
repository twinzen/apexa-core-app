package com.twinzom.apexa.api;

import java.util.concurrent.Executor;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
@EnableAsync
@ComponentScan({"com.twinzom.apexa.dao","com.twinzom.apexa.api"})
@EnableJpaRepositories("com.twinzom.apexa.dao.repository")
@EntityScan("com.twinzom.apexa.dao.jpo")
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
	
	@Bean(name = "taskExecutor1")
	public Executor taskExecutor1 () {
		
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1000);
		executor.setMaxPoolSize(1000);
		executor.setQueueCapacity(5000);
		executor.setThreadNamePrefix("CalEng-");
		executor.initialize();
		return executor;
	}
	
	@Bean(name = "taskExecutor2")
	public Executor taskExecutor2 () {
		
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1000);
		executor.setMaxPoolSize(1000);
		executor.setQueueCapacity(5000);
		executor.setThreadNamePrefix("CalEng-");
		executor.initialize();
		return executor;
	}
	
	@Bean
	public PoolingHttpClientConnectionManager poolingConnectionManager() {
		
		PoolingHttpClientConnectionManager connManager 
			= new PoolingHttpClientConnectionManager();
		connManager.setMaxTotal(1000);
		connManager.setDefaultMaxPerRoute(1000);
		return connManager;
	}
	
	@Bean
	public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
		return new ConnectionKeepAliveStrategy() {
			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
				HeaderElementIterator it = new BasicHeaderElementIterator
						(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
				while (it.hasNext()) {
					HeaderElement he = it.nextElement();
					String param = he.getName();
					String value = he.getValue();
					
					if (value != null && param.equalsIgnoreCase("timeout")) {
						return Long.parseLong(value) * 1000;
					}
				}
				return 5 * 1000;
			}
		};
	}
	
	@Bean
	public CloseableHttpClient httpClient() {
		
		return HttpClients.custom()
				.setConnectionManager(poolingConnectionManager())
				.setKeepAliveStrategy(connectionKeepAliveStrategy())
				.build();
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}
	
	@Bean
	public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		return clientHttpRequestFactory;
	}

}
