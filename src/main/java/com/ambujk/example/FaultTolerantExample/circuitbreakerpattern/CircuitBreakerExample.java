package com.ambujk.example.FaultTolerantExample.circuitbreakerpattern;

import com.ambujk.example.FaultTolerantExample.client.BackendServiceManager;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class CircuitBreakerExample {
    @Autowired
    Supplier<String> decoratedSupplier;
    @Autowired
    CircuitBreaker circuitBreaker;
    @Autowired
    BackendServiceManager backendServiceManager;



    public String testCircuitBreaker() {

        // Execute the decorated supplier and recover from any exception
        //return Try.ofSupplier(decoratedSupplier).recover(throwable -> "Hello from Recovery").get();

        // When you don't want to decorate your lambda expression,
        // but just execute it and protect the call by a CircuitBreaker.
        return circuitBreaker.executeSupplier(backendServiceManager::getBackendServiceResult);

    }

}
