package com.cheaito.springtesting.memo;

import com.cheaito.springtesting.memo.domain.Memo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.Pattern;
import java.util.Optional;

public interface MemoController {
    @GetMapping("/{id}")
    Memo getMemoById(
            @Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "Id must follow format 999-9999")
            @PathVariable String id);
}
