package com.project.marketplace.domain.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
class SecurityConfig {
  private final JwtAuthConverter jwtAuthConverter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(customizer -> customizer.requestMatchers("/websocket").permitAll().anyRequest().authenticated());

    http.oauth2ResourceServer(
            oAuth2Customizer ->
                oAuth2Customizer.jwt(
                    jwtCustomizer -> jwtCustomizer.jwtAuthenticationConverter(jwtAuthConverter)));

    http.sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http.cors(customizer -> customizer.configurationSource(getCorsConfiguration()));

    return http.build();
  }

  private CorsConfigurationSource getCorsConfiguration() {
    CorsConfiguration corsConfig = new CorsConfiguration();
    corsConfig.setAllowedOrigins(List.of("http://localhost:4200"));
    corsConfig.setAllowedMethods(List.of("*"));
    corsConfig.setAllowedHeaders(List.of("*"));
    corsConfig.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
    corsConfigurationSource.registerCorsConfiguration("/**", corsConfig);

    return corsConfigurationSource;
  }
}
