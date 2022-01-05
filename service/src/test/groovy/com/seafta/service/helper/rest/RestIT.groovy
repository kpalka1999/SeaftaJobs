package com.seafta.service.helper.rest

import com.seafta.service.helper.WebHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes=[WebHelper])
@ActiveProfiles(value = "test")
class RestIT extends Specification {


    @Autowired
    TestRestTemplate restTemplate

    @Autowired
    WebHelper webHelper
    
    def <T> ResponseEntity<T> httpDelete(String url, Class<T> type, Object... vars) {
        restTemplate.exchange(url, HttpMethod.DELETE, assignHeaders(), type, vars)
    }

    
    def <T> ResponseEntity<T> httpDelete(String url, Object object, Class<T> type, Object... vars) {
        String uri = webHelper.buildQuery(url, object).build().toUriString()
        restTemplate.exchange(uri, HttpMethod.DELETE, assignHeaders(), type, vars)
    }

    
    def <T> ResponseEntity<T> httpPost(String url, Object object, Class<T> type, Object... vars) {
        restTemplate.exchange(url, HttpMethod.POST, assignHeaders(object), type, vars)
    }

    
    def <T> ResponseEntity<T> httpGet(String url, ParameterizedTypeReference<T> type) {
        restTemplate.exchange(url, HttpMethod.GET, assignHeaders(), type)
    }

    
    def <T> ResponseEntity<T> httpGet(String url, Class<T> type, Object... vars) {
        restTemplate.exchange(url, HttpMethod.GET, assignHeaders(), type, vars)
    }

    
    def <T> ResponseEntity<T> httpGet(String url, Object object, ParameterizedTypeReference<T> type, Object... vars) {
        String uri = webHelper.buildQuery(url, object).build().toUriString()
        restTemplate.exchange(uri, HttpMethod.GET, assignHeaders(), type, vars)
    }

    
    def <T> ResponseEntity<T> httpGet(String url, Object object, Class<T> type, Object... vars) {
        String uri = webHelper.buildQuery(url, object).build().toUriString()
        restTemplate.exchange(uri, HttpMethod.GET, assignHeaders(), type, vars)
    }

    
    def <T> ResponseEntity<T> httpPut(String url, Object object, ParameterizedTypeReference<T> type, Object... vars) {
        restTemplate.exchange(url, HttpMethod.PUT, assignHeaders(object), type, vars)
    }

    
    def <T> ResponseEntity<T> httpPut(String url, Object object, Class<T> type, Object... vars) {
        restTemplate.exchange(url, HttpMethod.PUT, assignHeaders(object), type, vars)
    }

    
    def <T> ResponseEntity<T> httpPut(String url, Object query, Object object, ParameterizedTypeReference<T> type, Object... vars) {
        String uri = webHelper.buildQuery(url, object).build().toUriString()
        restTemplate.exchange(uri, HttpMethod.PUT, assignHeaders(object), type, vars)
    }

    
    def <T> ResponseEntity<T> httpPut(String url, Object query, Object object, Class<T> type, Object... vars) {
        String uri = webHelper.buildQuery(url, object).build().toUriString()
        restTemplate.exchange(uri, HttpMethod.PUT, assignHeaders(object), type, vars)
    }

    private static HttpEntity<?> assignHeaders(Object body = null) {
        HttpHeaders headers = new HttpHeaders()
        new HttpEntity<?>(body, headers)
    }
}
