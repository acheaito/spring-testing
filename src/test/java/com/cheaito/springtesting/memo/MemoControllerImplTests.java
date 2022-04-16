package com.cheaito.springtesting.memo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemoControllerImpl.class)
@ExtendWith(MockitoExtension.class)
public class MemoControllerImplTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemoService memoService;

    @Test
    void shouldReturnMemoByIdFromRepo() throws Exception {
        Memo memo1 = Memo.builder().id("1").text("My first memo").build();
        doReturn(Optional.of(memo1)).when(memoService).getMemo("1");
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(get("/memos/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(writer.writeValueAsString(memo1)));
    }

}
