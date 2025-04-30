package com.example.springsession.api;

import com.example.springsession.dto.MemoDto;
import com.example.springsession.entity.Memo;
import com.example.springsession.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memos")
@RequiredArgsConstructor
public class MemoRestApiController {
    @Autowired
    private final MemoService memoService;

    @PostMapping("")
    public ResponseEntity<Memo> add(@RequestBody MemoDto memoDto) {
        Memo added = memoService.add(memoDto);
        return (added != null) ?
                ResponseEntity.status(HttpStatus.OK).body(added)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("")
    public ResponseEntity<List<Memo>> getAll(){
        List<Memo> memos = memoService.getAll();
        return (memos != null) ?
                ResponseEntity.status(HttpStatus.OK).body(memos)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Memo> find(@PathVariable Long id) {
        Memo find = memoService.find(id);
        return (find != null) ?
                ResponseEntity.status(HttpStatus.OK).body(find)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemoDto> putUpdate(@PathVariable Long id, @RequestBody MemoDto memoDto) {
        MemoDto edit = memoService.putUpdate(id, memoDto);
        return (edit != null) ?
                ResponseEntity.status(HttpStatus.OK).body(edit)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemoDto> patchUpdate(@PathVariable Long id, @RequestBody MemoDto memoDto) {
        MemoDto edit = memoService.patchUpdate(id, memoDto);
        return (edit != null) ?
                ResponseEntity.status(HttpStatus.OK).body(edit)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<MemoDto> delete(@PathVariable Long id) {
        MemoDto delete = memoService.delete(id);
        return (delete != null) ?
                ResponseEntity.status(HttpStatus.OK).body(delete)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
