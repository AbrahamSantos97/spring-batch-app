package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableBatchProcessing
@EnableAsync
public class TesisbatchprocessApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesisbatchprocessApplication.class, args);
	}

}
