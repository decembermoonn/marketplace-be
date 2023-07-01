package com.project.marketplace.domain.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("keycloak")
public
class KeycloakPropertiesConfig {
  private Admin admin;
  private Client client;
  private String tokenUri;

  public record Client(String secret, String id) {}

  public record Admin(String username, String password, String apiUri) {}
}
