//package com.project.marketplace.domain.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@Configuration
//@EnableWebSocket
//@RequiredArgsConstructor
//public class WebSocketConfig implements WebSocketConfigurer {
//  private final CustomWebSocketHandler customWebSocketHandler;
//  private final CustomWebSocketInterceptor customWebSocketInterceptor;
//
//  @Override
//  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//    registry
//        .addHandler(customWebSocketHandler, "/websocket")
//        .addInterceptors(customWebSocketInterceptor)
//        .setAllowedOrigins("*");
//  }
//}
