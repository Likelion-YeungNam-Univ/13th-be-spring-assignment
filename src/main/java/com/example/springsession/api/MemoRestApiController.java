package com.example.springsession.api;

import com.example.springsession.dto.MemoDto;
import com.example.springsession.entity.Memo;
import com.example.springsession.repository.MemoRepository;
import com.example.springsession.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memos")
public class MemoRestApiController {
    @Autowired
    private final MemoService memoService;

    @PostMapping("")
    public ResponseEntity<Memo> add(@RequestBody MemoDto memoDto) {
        Memo added = memoService.add(dto);
        return (added != null) ?
                ResponseEntity.status(HttpStatus.OK).body(added)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("")
    public ResponseEntity<List<Memo>> getAll(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Memo> getOne(@PathVariable Long id) {
        Memo find = memoService.find(id);
        return (find != null) ? ResponseEntity.status(HttpStatus.OK).body(find)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Memo> allUpdate(@PathVariable Long id, @RequestBody MemoDto memoDto) {
        return null;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Memo> partUpdate(@PathVariable Long id, @RequestBody MemoDto memoDto) {
        return null;
    }

}
