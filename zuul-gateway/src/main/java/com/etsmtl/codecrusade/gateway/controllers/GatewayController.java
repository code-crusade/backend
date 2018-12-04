package com.etsmtl.codecrusade.gateway.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
@Slf4j
public class GatewayController {

    private OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    public GatewayController(OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.authorizedClientService = oAuth2AuthorizedClientService;
    }

    @GetMapping("/oauth2/redirect")
    public String redirectUrl(){
        return "redirect:/app";
    }


    @GetMapping("/oauth2/login")
    public String forceLogin(OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        authentication.getAuthorizedClientRegistrationId(),
                        authentication.getName());
        String userInfoEndpointUri = client.getClientRegistration()
                                           .getProviderDetails().getUserInfoEndpoint().getUri();

        if (!StringUtils.isEmpty(userInfoEndpointUri)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken()
                                                                     .getTokenValue());
            HttpEntity entity = new HttpEntity("", headers);
            ResponseEntity<Map> response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
            Map userAttributes = response.getBody();
        }
        return "redirect:/app";
    }
}
