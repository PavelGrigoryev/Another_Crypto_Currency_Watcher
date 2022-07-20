package com.pavel.anothercryptocurrencywatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AnotherCryptoCurrencyWatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnotherCryptoCurrencyWatcherApplication.class, args);
	}

}
