package com.ambujk.example.FaultTolerantExample.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class BackendServiceManagerImpl implements BackendServiceManager {
    @Override
    public String getBackendServiceResult() {
        log.info("Calling getBackendServiceResult..");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> exchange = null;
        try {
            exchange = restTemplate.exchange(new URI("http://localhost:8080/product/13860428"), HttpMethod.GET,
                    null, String.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return exchange.getBody();
    }
}
