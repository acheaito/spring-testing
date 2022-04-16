package com.cheaito.springtesting.memo;

import com.cheaito.springtesting.memo.domain.Memo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemoService {
    private final MemoRepo repo;

    public MemoService(MemoRepo repo) {
        this.repo = repo;
    }

    public Optional<Memo> getMemo(String id) {
        return repo.getMemo(id);
    }

    public List<Memo> getMemos() {
        return repo.getMemos();
    }
}
