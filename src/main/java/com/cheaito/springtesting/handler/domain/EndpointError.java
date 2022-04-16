package com.cheaito.springtesting.handler.domain;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;

@Jacksonized
@Builder
@Value
public class EndpointError {
    Instant timestamp;
    int status;
    String error;
}
