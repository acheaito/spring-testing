package com.cheaito.springtesting.memo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemoServiceTests {
    private MemoService subject;
    @Mock
    private MemoRepo mockRepo;


    @BeforeEach
    void setup() {
        subject = new MemoService(mockRepo);
    }

    @Test
    void shouldPassIdToRepo() {
        subject.getMemo("1");
        verify(mockRepo).getMemo("1");
    }

    @Test
    void shouldReturnMemoFromRepo() {
        Optional<Memo> memo1 = Optional.ofNullable(Memo.builder().id("1").text("memo1").build());
        doReturn(memo1).when(mockRepo).getMemo("1");
        Optional<Memo> actual = subject.getMemo("1");
        assertThat(actual).isPresent().hasValue(memo1.orElse(null));
    }

    @Test
    void shouldReturnAllMemosFromRepo() {
        Memo memo1 = Memo.builder().id("1").text("memo1").build();
        List<Memo> expected = List.of(memo1);
        doReturn(expected).when(mockRepo).getMemos();

        List<Memo> actual = subject.getMemos();
        assertThat(actual).isEqualTo(expected);
    }
}
