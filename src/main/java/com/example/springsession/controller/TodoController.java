package com.example.springsession.controller;


import com.example.springsession.domain.Todo;
import com.example.springsession.dto.createTodo;
import com.example.springsession.dto.patchTodo;
import com.example.springsession.dto.putTodo;
import com.example.springsession.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //할일 생성
    @PostMapping
    public ResponseEntity<Todo> create(@RequestBody createTodo createtodo) {
        Todo newOne = todoService.create(createtodo);
        return ResponseEntity.ok(newOne);
    }

    //전체 조회
    @GetMapping
    public ResponseEntity<List<Todo>> getAll() {
        return ResponseEntity.ok(todoService.getAll());
    }

    //개별조회
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getById(@PathVariable Long id) {
        Todo todo = todoService.getById(id);
        if (todo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(todo);
    }

    //patch(일부분만) 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Todo> patch(@PathVariable Long id, @RequestBody patchTodo patchtodo) {
        Todo todo = todoService.patchTodo(id, patchtodo);
        if(todo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(todo);
    }

    //put(덮어쓰기)
    @PutMapping("/{id}")
    public ResponseEntity<Todo> put(@PathVariable Long id, @RequestBody putTodo puttodo) {
        Todo todo = todoService.put(id, puttodo);
        if(todo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(todo);
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> delete(@PathVariable Long id) {
        Todo todo = todoService.delete(id);
        if(todo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(todo);
    }



}
