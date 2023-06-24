package com.project.marketplace.ports.outbound;

import com.project.marketplace.domain.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
}
