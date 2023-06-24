package com.project.marketplace.domain.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
class SecurityConfig {
  private final JwtAuthConverter jwtAuthConverter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(customizer -> customizer.anyRequest().authenticated());

    http.oauth2ResourceServer(
            oAuth2Customizer ->
                oAuth2Customizer.jwt(
                    jwtCustomizer -> jwtCustomizer.jwtAuthenticationConverter(jwtAuthConverter)));

    http.sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }
}
