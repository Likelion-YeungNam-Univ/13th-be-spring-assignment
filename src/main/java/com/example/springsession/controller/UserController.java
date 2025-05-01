package com.example.springsession.controller;


import com.example.springsession.dto.UserDto;
import com.example.springsession.entity.User;
import com.example.springsession.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @PostMapping // 사용자 생성
    public ResponseEntity<Long> createUser(@RequestBody UserDto dto) {
        Long id = userService.createUser(dto);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}") // 사용자 조회
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping // 사용자 일부 조회
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}") // 사용자 업데이터
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
        userService.updateUser(id, dto);
        return ResponseEntity.ok(id + " 사용자의 정보가 수정되었습니다.");
    }

    @DeleteMapping("/{id}") // 사용자 삭제
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(id + " 사용자가 삭제되었습니다.");
    }
}
