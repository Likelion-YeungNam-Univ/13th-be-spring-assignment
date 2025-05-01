package com.example.springsession.controller;

import com.example.springsession.model.Todo;
import com.example.springsession.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    //DI
    private TodoService service;
    public TodoController(TodoService service){
        this.service = service;
    }
    @GetMapping("")
    public List<Todo> allView(){
        return service.allView();
    }
    @PostMapping("")
    public List<Todo> todoAdd(@RequestBody Todo task){
        return service.todoAdd(task);
    }
    @PutMapping("")
    public List<Todo> edit(@RequestBody Todo task){
        return service.edit(task);
    }
    @PatchMapping("/{id}")
    public List<Todo> completedEdit(@PathVariable int id){
        return service.completedEdit(id);
    }
    @DeleteMapping("/{id}")
    public List<Todo> deleteTodo(@PathVariable int id){
        return service.deleteTodo(id);
    }
}
