package com.whiteSwanSecurity.controller;

import com.whiteSwanSecurity.dto.UserDetails;
import com.whiteSwanSecurity.dto.UtilityModel;
import com.whiteSwanSecurity.service.AzureADIntegrationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/azureAd")
@Slf4j
public class AzureADIntegrationController {
    private final AzureADIntegrationService azureADIntegrationService;

    @Autowired
    public AzureADIntegrationController(AzureADIntegrationService azureADIntegrationService) {
        this.azureADIntegrationService = azureADIntegrationService;
    }


    @Operation(summary = "Get access token from Github")
    @GetMapping("/accessToken/get")
    public UtilityModel getAccessToken(OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(), authentication.getName());

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        String tokenValue = accessToken.getTokenValue();

        System.out.println("Token: " + tokenValue);

        UtilityModel model = new UtilityModel();
        model.setAccessToken(tokenValue);
        return model;
    }


    @GetMapping("/getUsers")
    public List<UserDetails> getUsersHandler(@RequestParam String at) {
        log.info("Into controller..");
        return azureADIntegrationService.getUsers(at);
    }
}
