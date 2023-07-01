package com.project.marketplace.adapters.outbound;

import com.project.marketplace.domain.config.KeycloakPropertiesConfig;
import com.project.marketplace.domain.model.User;
import com.project.marketplace.ports.outbound.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class KeycloakUserRepository implements UserRepository {
    private final KeycloakPropertiesConfig keycloakPropertiesConfig;

    // TODO refactor
    @Override
    public List<User> findAll() {
        // Create WebClient
        WebClient webClient = WebClient.builder()
                .baseUrl(keycloakPropertiesConfig.getAdmin().apiUri())
                .defaultHeader("Authorization", "Bearer " + getAccessToken())
                .build();

        // Fetch user list
        return webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block();
    }

    private String getAccessToken() {
        String tokenUri = keycloakPropertiesConfig.getTokenUri();

        // Create WebClient
        WebClient webClient = WebClient.builder()
                .baseUrl(tokenUri)
                .build();

        // Build request body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "password");
        requestBody.add("username", keycloakPropertiesConfig.getAdmin().username());
        requestBody.add("password", keycloakPropertiesConfig.getAdmin().password());
        requestBody.add("client_id", keycloakPropertiesConfig.getClient().id());
        requestBody.add("client_secret", keycloakPropertiesConfig.getClient().secret());

        // Retrieve access token
        AccessToken response = webClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(requestBody))
                .retrieve()
                .bodyToMono(AccessToken.class)
                .block();

        return response.access_token;
    }

    record AccessToken(String access_token) {}
}
