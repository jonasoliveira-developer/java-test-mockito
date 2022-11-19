package com.jonas.api.services;

import com.jonas.api.domain.User;
import org.springframework.stereotype.Service;

public interface UserService {

    User findById(Integer id);
}
