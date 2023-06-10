package com.project.marketplace.adapters.inbound.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
class ExampleApiController implements ExampleApi {
    @Override
    public void exampleGet() {
      log.warn("hello");
    }
}
