package com.project.marketplace.adapters.inbound.controllers;

import com.project.marketplace.ports.inbound.GetUsersResult;
import com.project.marketplace.ports.outbound.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class UserController {
  static final String API_USERS = "users";
  private final UserRepository userRepository;

  @GetMapping(API_USERS)
  public GetUsersResult getUsers() {
    return new GetUsersResult(
        userRepository.findAll().stream()
            .map(user -> new GetUsersResult.UserResult(user.id(), user.email()))
            .toList());
  }
}
