package com.whiteSwanSecurity.service;


import com.whiteSwanSecurity.dto.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;


import java.util.Arrays;
import java.util.List;


import java.util.Collections;

@Service
@Slf4j
public class AzureADIntegrationService {
    private final RestTemplate restTemplate;
    private static final String GRAPH_API_URL = "https://graph.microsoft.com/v1.0/users";



    @Autowired
    public AzureADIntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<UserDetails> getUsers(String accessToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<UserDetails[]> response = restTemplate.exchange(GRAPH_API_URL, HttpMethod.GET, entity, UserDetails[].class);

            return Arrays.asList(response.getBody());
        } catch (RestClientException e) {
            log.error("RestClientException: {}", e.getMessage());
            return Collections.emptyList();
        }catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

}
