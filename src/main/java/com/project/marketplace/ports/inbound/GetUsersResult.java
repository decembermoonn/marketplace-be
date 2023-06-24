package com.project.marketplace.ports.inbound;

import java.util.List;

public record GetUsersResult(List<UserResult> users) {
  public record UserResult(String id, String email) {}
}
