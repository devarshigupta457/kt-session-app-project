package com.project.ktsession.KTSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class KtSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(KtSessionApplication.class, args);
	}

}