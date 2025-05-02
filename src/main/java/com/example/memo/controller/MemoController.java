package com.example.memo.controller;

import com.example.memo.model.Memo;
import com.example.memo.repository.MemoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/memos")

public class MemoController {
    // 임시 저장소 객체 생성
    private final MemoRepository repository = new MemoRepository();

    // 전체 메모 조회
    @GetMapping
    public List<Memo> getAllMemos() {
        return repository.findAll();
    }

    // 특정 ID의 메모 조회
    @GetMapping("/{id}")
    public Memo getMemo(@PathVariable Long id) {
        return repository.findById(id);
    }

    // 새 메모 생성
    @PostMapping
    public Memo createMemo(@RequestBody Memo memo) {
        return repository.save(memo);
    }

    // 메모 수정
    @PutMapping("/{id}")
    public void updateMemo(@PathVariable Long id, @RequestBody Memo memo) {
        repository.update(id, memo);
    }

    // 메모 삭제
    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id) {
        repository.delete(id);
    }
}
