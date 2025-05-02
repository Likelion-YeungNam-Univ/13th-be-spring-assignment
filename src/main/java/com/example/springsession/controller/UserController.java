package com.example.springsession.controller;


import com.example.springsession.dto.UserDto;
import com.example.springsession.entity.User;
import com.example.springsession.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @PostMapping // 사용자 생성
    public ResponseEntity<Long> createUser(@RequestBody UserDto dto) {
        Long id = userService.createUser(dto);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}") // 사용자 일부 조회
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        try {
            User user = userService.getUser(id);
            return ResponseEntity.ok(user);
        } catch (NoSuchElementException e) { // 요소가 없는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("사용자를 찾을 수 없습니다. ID: " + id);
        }
    }

    @GetMapping // 사용자 조회
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}") // 사용자 업데이트
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
        try {
            userService.updateUser(id, dto);
            return ResponseEntity.ok(id + " 사용자의 정보가 수정되었습니다.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("사용자를 찾을 수 없습니다. ID: " + id);
        }
    }

    @DeleteMapping("/{id}") // 사용자 삭제
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(id + " 사용자가 삭제되었습니다.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("사용자를 찾을 수 없습니다. ID: " + id);
        }
    }
}
