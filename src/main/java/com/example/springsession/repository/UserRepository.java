package com.example.springsession.repository;


import com.example.springsession.dto.UserDto;
import com.example.springsession.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    private final Map<Long, User> repo = new HashMap<>();
    private long sequence = 1L;

    public Long save(User user) { // 유저 생성
        Long id = sequence++;
        user.setId(id);
        repo.put(id, user);
        return id;
    }

    public User findById(Long id) { // 유저 조회
        return repo.get(id); // null일 수 있음
    }

    public List<User> findAll() { // 유저 전체 조회
        return new ArrayList<>(repo.values());
    }


    public void update(Long id, UserDto dto) { // 유저 업데이트
        User user = repo.get(id);
        if (user != null) {
            user.setName(dto.getName());
            user.setAge(dto.getAge());
            user.setIntro(dto.getIntro());
        }
    }

    public void delete(Long id) { // 유저 삭제
        repo.remove(id);
    }
}
