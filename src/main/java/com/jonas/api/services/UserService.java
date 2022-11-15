package com.jonas.api.services;

import com.jonas.api.domain.User;

public interface UserService {

    User findById(Integer id);
}
