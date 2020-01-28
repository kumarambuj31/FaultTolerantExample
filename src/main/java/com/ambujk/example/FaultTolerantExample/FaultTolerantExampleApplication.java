package com.ambujk.example.FaultTolerantExample;

import com.ambujk.example.FaultTolerantExample.client.BackendServiceManager;
import com.ambujk.example.FaultTolerantExample.client.BackendServiceManagerImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FaultTolerantExampleApplication {

	@Bean
	BackendServiceManager backendServiceManager(){
		return  new BackendServiceManagerImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(FaultTolerantExampleApplication.class, args);
	}

}
