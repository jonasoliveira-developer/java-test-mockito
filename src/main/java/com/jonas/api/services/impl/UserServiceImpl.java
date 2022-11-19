package com.jonas.api.services.impl;

import com.jonas.api.domain.User;
import com.jonas.api.repositories.UserRepository;
import com.jonas.api.services.UserService;
import com.jonas.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public User findById(Integer id) {
         Optional<User> obj = repository.findById(id);
         return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
    }
}
