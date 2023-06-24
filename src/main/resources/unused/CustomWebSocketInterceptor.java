//package com.project.marketplace.domain.config;
//
//import java.util.Map;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//@Slf4j
//@Component
//public class CustomWebSocketInterceptor implements HandshakeInterceptor {
//  @Override
//  public boolean beforeHandshake(
//      ServerHttpRequest request,
//      ServerHttpResponse response,
//      WebSocketHandler wsHandler,
//      Map<String, Object> attributes)
//      throws Exception {
//    // Extract the query parameters from the request URI
//    String queryString = request.getURI().getQuery();
//    // Parse the query string and extract the required parameter(s)
//    String parameterValue = extractParameterValue(queryString, "testparam");
//    // Store the parameter value in the attributes map to pass it to the WebSocketHandler
//    attributes.put("parameterName", parameterValue);
//    return true;
//  }
//
//  @Override
//  public void afterHandshake(
//      ServerHttpRequest request,
//      ServerHttpResponse response,
//      WebSocketHandler wsHandler,
//      Exception exception) {}
//
//  private String extractParameterValue(String queryString, String parameterName) {
//    // Implement your own logic to parse the query string and extract the parameter value
//    // Here's a simple example assuming the query string is in the format "key1=value1&key2=value2"
//    String[] keyValues = queryString.split("&");
//    for (String keyValue : keyValues) {
//      String[] parts = keyValue.split("=");
//      if (parts.length == 2 && parts[0].equals(parameterName)) {
//        return parts[1];
//      }
//    }
//    return null; // Parameter not found
//  }
//}
