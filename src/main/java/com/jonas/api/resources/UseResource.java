package com.jonas.api.resources;

import com.jonas.api.domain.User;
import com.jonas.api.domain.dto.UserDto;
import com.jonas.api.services.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UseResource {


    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserServiceImpl service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping(value = ID)
    public ResponseEntity<UserDto> findById(@PathVariable Integer id) {
        return  ResponseEntity.ok().body(mapper.map(service.findById(id),UserDto.class));
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok().body(service.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public  ResponseEntity<UserDto> create(@RequestBody UserDto obj) {
        User newObj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PutMapping(value = ID)
    public ResponseEntity<UserDto> update(@PathVariable Integer id, @RequestBody UserDto obj) {
        obj.setId(id);
        return ResponseEntity.ok().body(mapper.map( service.update(obj), UserDto.class));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = ID)
    public ResponseEntity<UserDto> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }





}
