package com.example.springsession.service;


import com.example.springsession.dto.UserDto;
import com.example.springsession.entity.User;
import com.example.springsession.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

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
        return userRepository.findById(id);
    }

    public List<User> getAllUser() { // 사용자 전체 조회
        return userRepository.findAll();
    }

    public void updateUser(Long id, UserDto dto) { // 사용자 업데이트
        userRepository.update(id, dto);
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }


}
