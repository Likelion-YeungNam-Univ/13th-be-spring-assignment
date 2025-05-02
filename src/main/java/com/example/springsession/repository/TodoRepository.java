package com.example.springsession.repository;

import com.example.springsession.domain.Todo;
import com.example.springsession.dto.patchTodo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TodoRepository {
    private final Map<Long, Todo> todoDB = new HashMap<>();


    public Todo save(Todo todo) { //저장
        todoDB.put(todo.getId(), todo);
        return todo;
    }

    public List<Todo> findAll() { //전체 값들 조회
        return new ArrayList<>(todoDB.values());
    }

    public Todo findById(Long id) { //id로 개별 조회
        return todoDB.get(id);
    }

    public void delete(Long id) { //해당 id의 값 삭제
        todoDB.remove(id);
    }
}
