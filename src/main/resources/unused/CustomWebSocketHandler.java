//package com.project.marketplace.domain.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.*;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//@Slf4j
//@Component
//public class CustomWebSocketHandler extends TextWebSocketHandler {
//  @Override
//  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//    log.info("Session %s established".formatted(session.getId()));
//  }
//
//  @Override
//  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//    log.warn(
//        "Session %s closed with code %d because of %s"
//            .formatted(session.getId(), status.getCode(), status.getReason()));
//  }
//
//  @Override
//  protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
//    super.handleBinaryMessage(session, message);
//  }
//
//  @Override
//  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//    String payload = message.getPayload();
//    log.info(session.getAcceptedProtocol());
//    session.getAttributes().values().forEach(k -> log.warn(k.toString()));
//    log.info(payload);
//    session.getExtensions().forEach(e -> log.warn(e.getName()));
//    session.getHandshakeHeaders().forEach((x, y) -> log.warn(x + ":::" + y));
////    log.warn(session.getPrincipal().toString());
//  }
//
//  @Override
//  public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
//      throws Exception {
//    super.handleMessage(session, message);
//  }
//
//  @Override
//  public boolean supportsPartialMessages() {
//    return super.supportsPartialMessages();
//  }
//
//  @Override
//  public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
//    log.error("error occured at sender " + session, throwable);
//    // ...
//  }
//
//  @Override
//  protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
//    super.handlePongMessage(session, message);
//  }
//}
