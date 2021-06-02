package com.rosajr.br.service;

import com.rosajr.br.dto.UserInputDTO;
import com.rosajr.br.entity.User;

public interface UserService {
    void create(UserInputDTO dto);

    User getUserById(Long id);
}
