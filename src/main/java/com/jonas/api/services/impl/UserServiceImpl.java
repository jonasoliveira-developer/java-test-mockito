package com.jonas.api.services.impl;

import com.jonas.api.domain.User;
import com.jonas.api.domain.dto.UserDto;
import com.jonas.api.repositories.UserRepository;
import com.jonas.api.services.UserService;
import com.jonas.api.services.exceptions.DataIntegratyViolationException;
import com.jonas.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
         Optional<User> obj = repository.findById(id);
         return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
    }


    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserDto obj) {
        findByEmail(obj);
        obj.setPassword( new BCryptPasswordEncoder().encode(obj.getPassword())) ;
        return repository.save(mapper.map(obj, User.class));
    }

    @Override
    public User update(UserDto obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
         repository.deleteById(id);
    }

    private void findByEmail(UserDto obj) {
        Optional<User> user = repository.findByEmail(obj.getEmail());
        if (user.isPresent() && !user.get().getId().equals(obj.getId())) {
            throw new DataIntegratyViolationException("E-mail já cadastrado!");
        }
    }
}
