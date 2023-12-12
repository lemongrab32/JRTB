package com.github.javarushcommunity.javarushtelegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JavarushTelegrambotApplication {


	public static void main(String[] args) {
		SpringApplication.run(JavarushTelegrambotApplication.class, args);
	}

}
