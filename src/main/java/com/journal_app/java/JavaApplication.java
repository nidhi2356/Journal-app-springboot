package com.journal_app.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.web.client.RestTemplate;

@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
public class JavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaApplication.class, args);
	}

	@Bean
    public PlatformTransactionManager falana(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	//We create a RestTemplate bean so Spring can manage and inject a
	//single reusable RestTemplate instance throughout the application.

}



