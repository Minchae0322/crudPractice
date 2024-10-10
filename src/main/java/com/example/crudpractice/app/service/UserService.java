package com.example.crudpractice.app.service;


import com.example.crudpractice.app.core.exception.ResponseException;
import com.example.crudpractice.app.domain.User;
import com.example.crudpractice.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseException("USER NOT FOUND", HttpStatus.NOT_FOUND));
    }
}
