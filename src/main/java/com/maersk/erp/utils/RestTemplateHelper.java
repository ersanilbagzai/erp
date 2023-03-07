package com.maersk.erp.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class RestTemplateHelper {

    private static final Logger log = LoggerFactory.getLogger(RestTemplateHelper.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public <T, R> T postForEntity(String url, R body, HttpHeaders headers, Class<T> clazz) throws RestClientException, IOException {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url);

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<R> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(uriBuilder.toUriString(), request, String.class);
        JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
        return readValue(response, javaType);
    }

    public <T> T exchange(HttpMethod httpMethod, String url, HttpHeaders headers, Class<T> clazz, Object... uriVariables) throws RestClientException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url);

        headers.setContentType(MediaType.APPLICATION_JSON);

        return restTemplate.exchange(uriBuilder.toUriString(), httpMethod,
                new HttpEntity<>(headers), clazz, uriVariables).getBody();
    }

    private <T> T readValue(ResponseEntity<String> response, JavaType javaType) throws IOException {
        T result = null;
        if (response.getStatusCode() == HttpStatus.OK ||
                response.getStatusCode() == HttpStatus.CREATED) {
            result = objectMapper.readValue(response.getBody(), javaType);
        } else {
            log.info("No data found {}", response.getStatusCode());
        }
        return result;
    }
}
