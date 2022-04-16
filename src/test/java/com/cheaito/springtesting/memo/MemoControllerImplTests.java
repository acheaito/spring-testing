package com.cheaito.springtesting.memo;

import com.cheaito.springtesting.memo.domain.Memo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemoControllerImpl.class)
@ExtendWith(MockitoExtension.class)
public class MemoControllerImplTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemoService memoService;

    @Test
    void shouldReturnMemoByIdFromRepo() throws Exception {
        String memoId = "100-0001";
        Memo memo1 = Memo.builder().id(memoId).text("My first memo").build();
        doReturn(Optional.of(memo1)).when(memoService).getMemo(memoId);
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(get("/memos/{id}", memoId))
                .andExpect(status().isOk())
                .andExpect(content().json(writer.writeValueAsString(memo1)));
    }

    @Test
    void shouldReturn404WhenNoMemoIsFoundForId() throws Exception {
        String id = "100-0000";
        mockMvc.perform(get("/memos/{id}", id))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.status", Matchers.equalTo(HttpStatus.NOT_FOUND.value())))
                .andExpect(jsonPath("$.error", Matchers.equalTo("Memo with id " + id + " was not found")));
    }

    @Test
    void shouldReturn400ForBadlyFormattedId() throws Exception {
        mockMvc.perform(get("/memos/{id}", "bad-id"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.status", Matchers.equalTo(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.error", Matchers.equalTo("getMemoById.id: Id must follow format 999-9999")));
    }

}
