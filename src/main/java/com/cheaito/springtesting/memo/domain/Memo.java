package com.cheaito.springtesting.memo.domain;


import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class Memo {
    String id;
    String text;
}
