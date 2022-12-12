package com.jonas.api.services;

import com.jonas.api.domain.User;
import com.jonas.api.domain.dto.UserDto;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User create(UserDto obj);
}
