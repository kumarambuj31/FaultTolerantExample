package com.ambujk.example.FaultTolerantExample.config;

import com.ambujk.example.FaultTolerantExample.client.BackendServiceManager;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.function.Supplier;

@Configuration
public class CircuitBreakerConfig {

    @Autowired
    BackendServiceManager backendServiceManager;

    @Bean
    public CircuitBreaker circuitBreaker() {

        CircuitBreakerRegistry circuitBreakerRegistry= CircuitBreakerRegistry.ofDefaults();

        io.github.resilience4j.circuitbreaker.CircuitBreakerConfig defaultConfig = circuitBreakerRegistry.getDefaultConfig();

        io.github.resilience4j.circuitbreaker.CircuitBreakerConfig overwrittenConfig = io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
                .from(defaultConfig)
                .failureRateThreshold(3)
                .waitDurationInOpenState(Duration.ofSeconds(5))
                .slidingWindow(10, 1, io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .build();

        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(overwrittenConfig);
        return registry.circuitBreaker("getProduct-circuit-braker");
    }

    @Bean("decoratedSupplier")
    public Supplier<String> getDecoratedSupplier() {
        return  CircuitBreaker.decorateSupplier(circuitBreaker(), backendServiceManager::getBackendServiceResult);
    }
}
