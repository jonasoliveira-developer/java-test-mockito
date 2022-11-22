package com.jonas.api.resources;

import com.jonas.api.domain.User;
import com.jonas.api.domain.dto.UserDto;
import com.jonas.api.services.UserService;
import com.jonas.api.services.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UseResource {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserServiceImpl service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id) {
        return  ResponseEntity.ok().body(mapper.map(service.findById(id),UserDto.class));
    }
}
