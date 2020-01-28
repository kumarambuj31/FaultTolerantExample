package com.ambujk.example.FaultTolerantExample.controller;

import com.ambujk.example.FaultTolerantExample.bulkheadpattern.BulkHeadExample;
import com.ambujk.example.FaultTolerantExample.circuitbreakerpattern.CircuitBreakerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResilliancyController {

    @Autowired
    CircuitBreakerExample circuitBreakerExample;
    @Autowired
    BulkHeadExample bulkHeadExample;

    @RequestMapping("/test1")
    public String test1(){
        return circuitBreakerExample.testCircuitBreaker();
    }

    @RequestMapping("/test2")
    public String test2(){
        return bulkHeadExample.testBulkHeadPattern();
    }
}
