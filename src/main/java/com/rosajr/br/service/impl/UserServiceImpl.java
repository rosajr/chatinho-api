package com.rosajr.br.service.impl;

import com.rosajr.br.dto.UserInputDTO;
import com.rosajr.br.entity.User;
import com.rosajr.br.exceptions.ObjectNotFoundException;
import com.rosajr.br.repository.UserRepository;
import com.rosajr.br.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void create(UserInputDTO dto) {

        var passwordEncoder = new BCryptPasswordEncoder();

        var encodedPassword = passwordEncoder.encode(dto.getPassword());

        var user = User.builder()
                .name(dto.getName())
                .nickName(dto.getNickName())
                .password(encodedPassword).build();

        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found for id: " + id));
    }
}