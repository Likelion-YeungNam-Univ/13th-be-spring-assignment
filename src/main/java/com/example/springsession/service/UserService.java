package com.example.springsession.service;


import com.example.springsession.dto.UserDto;
import com.example.springsession.entity.User;
import com.example.springsession.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long createUser(UserDto dto) { // 유저 생성
        User user = new User();
        user.setName(dto.getName());
        user.setAge(dto.getAge());
        user.setIntro(dto.getIntro());
        return userRepository.save(user);
    }

    public User getUser(Long id) { // 사용자 조회
        User user = userRepository.findById(id);
        if (user == null) {
            throw new NoSuchElementException(id + "의 유저는 존재하지않습니다.");
        }
        return user;
    }

    public List<User> getAllUser() { // 사용자 전체 조회
        return userRepository.findAll();
    }

    public void updateUser(Long id, UserDto dto) { // 사용자 업데이트
        User user = userRepository.findById(id);
        if (user == null) {
            throw new NoSuchElementException(id + "의 유저는 존재하지않습니다.");
        }
        userRepository.update(id, dto);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new NoSuchElementException(id + "의 유저는 존재하지않습니다.");
        }
        userRepository.delete(id);
    }
}
