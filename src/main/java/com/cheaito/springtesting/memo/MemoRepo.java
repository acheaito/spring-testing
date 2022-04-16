package com.cheaito.springtesting.memo;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class MemoRepo {
    private final Map<String, Memo> memos;

    public MemoRepo() {
        memos = new HashMap<>();
        memos.put("1", Memo.builder().id("1").text("My first memo").build());
        memos.put("2", Memo.builder().id("2").text("My second memo").build());
    }

    public List<Memo> getMemos() {
        return List.copyOf(memos.values());
    }

    public Optional<Memo> getMemo(String id) {
        return Optional.ofNullable(memos.get(id));
    }
}
