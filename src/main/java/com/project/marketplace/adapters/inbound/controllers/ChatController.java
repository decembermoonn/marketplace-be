package com.project.marketplace.adapters.inbound.controllers;

import com.project.marketplace.domain.model.ChatMessage;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
class ChatController {
  private final SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/chat/{receiverId}")
  @SendToUser()
  public void getMessage(
      Message<ChatMessage> message, @DestinationVariable String receiverId, Principal principal) {
    messagingTemplate.convertAndSend(
        "/topic/chat/%s/%s".formatted(principal.getName(), receiverId), message.getPayload());
  }
}
