package com.project.marketplace.domain.model;

public record ChatMessage(ChatMessageType type, String content) {
  public enum ChatMessageType {
    MESSAGE,
    TYPING
  }
}
