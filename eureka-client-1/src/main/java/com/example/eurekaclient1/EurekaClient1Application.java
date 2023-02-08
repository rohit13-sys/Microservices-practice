package com.example.eurekaclient1;

import com.example.eurekaclient1.Config.WikimediaChangesHandler;
import com.example.eurekaclient1.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class EurekaClient1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClient1Application.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private KafkaProducerService kafkaProducerService;

	@Override
	public void run(String... args) throws Exception {
//		kafkaProducerService.sendMessage();
	}
}
