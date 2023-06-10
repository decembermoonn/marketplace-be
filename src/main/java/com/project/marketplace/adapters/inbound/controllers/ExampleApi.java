package com.project.marketplace.adapters.inbound.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

interface ExampleApi {
    @Operation(
            operationId = "exampleGet",
            summary = "Get a greeting message",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/hello",
            produces = { "application/json" }
    )
    void exampleGet();
}
