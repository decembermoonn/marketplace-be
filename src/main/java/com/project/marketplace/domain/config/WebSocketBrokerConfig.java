package com.project.marketplace.domain.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketBrokerConfig implements WebSocketMessageBrokerConfigurer {
  private final JwtAuthConverter jwtAuthConverter;
  private final JwtDecoder jwtDecoder;

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/websocket").setAllowedOrigins("*");
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.enableSimpleBroker("/topic");
    registry.setApplicationDestinationPrefixes("/app");
  }

  @Override
  public void configureClientInboundChannel(ChannelRegistration registration) {
    registration.interceptors(
        new ChannelInterceptor() {
          @Override
          public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {
            StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

            if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
              String token = getAuthTokenFromHeaders(message.getHeaders());

              // token is probably valid, because underneath i think that there might be
              // NimbusJwtDecoder.withJwkSetUri(jwkSetUri) which performs request to check
              // if token exists (probably....)
              // todo - verify this
              Authentication auth = jwtAuthConverter.convert(jwtDecoder.decode(token));
              SecurityContextHolder.getContext().setAuthentication(auth);
              accessor.setUser(auth);
            }
            return message;
          }

          private String getAuthTokenFromHeaders(MessageHeaders headers) {
            var nativeHeaders = headers.get("nativeHeaders", LinkedMultiValueMap.class);
            if (nativeHeaders != null) {
              List<?> authHeader = nativeHeaders.get("Authorization");
              if (authHeader != null) {
                return authHeader.get(0).toString().substring(7);
              }
            }
            return null;
          }
        });
  }
}
