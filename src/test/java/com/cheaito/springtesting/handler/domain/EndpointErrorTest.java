package com.cheaito.springtesting.handler.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class EndpointErrorTest {

    @Test
    void shouldReturnStringRepresentation() {
        Instant now = Instant.now();
        EndpointError subject = EndpointError.builder()
                .timestamp(now)
                .status(200)
                .error("test1").build();
        assertThat(subject.toString()).isEqualTo("EndpointError(timestamp=" + now.toString()+ ", status=200, error=test1)");
    }

    @Test
    void shouldEqualOnFieldValues() {
        Instant now = Instant.now();
        EndpointError subject1 = EndpointError.builder()
                .timestamp(now)
                .status(200)
                .error("test1").build();
        EndpointError subject2 = EndpointError.builder()
                .timestamp(now)
                .status(200)
                .error("test1").build();
        assertThat(subject1.equals(subject2)).isTrue();
    }

}
