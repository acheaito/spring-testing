package com.cheaito.springtesting.memo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoRepoTests {
    private List<Memo> memoList;
    private MemoRepo subject;

    @BeforeEach
    void setup() {
        subject = new MemoRepo();
        memoList = List.of(
                Memo.builder().id("1").text("My first memo").build(),
                Memo.builder().id("2").text("My second memo").build()
        );
    }
    @Test
    void shouldReturnAllMemos() {
        List<Memo> actual = subject.getMemos();
        assertThat(actual).isEqualTo(memoList);
    }

    @Test
    void shouldReturnMemoForId() {
        Optional<Memo> actual = subject.getMemo("1");
        assertThat(actual).isPresent().hasValue(memoList.get(0));
    }

    @Test
    void shouldReturnEmptyOptionalForInvalidId() {
        Optional<Memo> actual = subject.getMemo("777");
        assertThat(actual).isNotPresent();
    }
}
