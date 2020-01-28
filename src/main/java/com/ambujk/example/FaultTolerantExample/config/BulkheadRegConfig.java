package com.ambujk.example.FaultTolerantExample.config;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.vavr.CheckedFunction0;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class BulkheadRegConfig {

    @Bean
    public Bulkhead bulkhead() {

        BulkheadRegistry bulkheadRegistry = BulkheadRegistry.ofDefaults();
        //ThreadPoolBulkheadRegistry threadPoolBulkheadRegistry = ThreadPoolBulkheadRegistry.ofDefaults();

        // Create a custom configuration for a Bulkhead
        BulkheadConfig custom = BulkheadConfig.from(bulkheadRegistry.getDefaultConfig())
                .maxConcurrentCalls(5)
                .maxWaitDuration(Duration.ofMillis(500))
                .build();

        // Create a BulkheadRegistry with a custom global configuration
        BulkheadRegistry registry = BulkheadRegistry.of(custom);

        // Get or create a Bulkhead from the registry -
        // bulkhead will be backed by the default config
        //Bulkhead bulkheadWithDefaultConfig = registry.bulkhead("name1");
        // Get or create a Bulkhead from the registry,
        // use a custom configuration when creating the bulkhead
        return registry.bulkhead("name2", custom);
    }

    /*@Bean
    public CheckedFunction0<String> BulkHeadDecoratedSupplier() {
        //CheckedFunction0<String> decoratedSupplier = Bulkhead.decorateCheckedSupplier(bulkhead(), () -> "This can be any method which returns: 'Hello");
        return Bulkhead.decorateCheckedSupplier(bulkhead(), () -> "This can be any method which returns: 'Hello");

    }*/


}
