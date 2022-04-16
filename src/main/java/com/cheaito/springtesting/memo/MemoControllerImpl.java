package com.cheaito.springtesting.memo;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.Optional;

@RestController
@RequestMapping(path = "/memos",
        produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class MemoControllerImpl implements MemoController {
    private final MemoService memoService;

    public MemoControllerImpl(MemoService memoService) {
        this.memoService = memoService;
    }

    @Override
    @GetMapping("/{id}")
    public Optional<Memo> getMemoById(
            @Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "Id must follow format 999-9999")
            @PathVariable String id) {
        return memoService.getMemo(id);
    }
}
