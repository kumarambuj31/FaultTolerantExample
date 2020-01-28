package com.ambujk.example.FaultTolerantExample.bulkheadpattern;

import com.ambujk.example.FaultTolerantExample.client.BackendServiceManager;
import io.github.resilience4j.bulkhead.Bulkhead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BulkHeadExample {

    @Autowired
    Bulkhead bulkhead;
    @Autowired
    BackendServiceManager backendServiceManager;

    public String testBulkHeadPattern(){
        return bulkhead.executeSupplier(backendServiceManager::getBackendServiceResult);
    }
}
