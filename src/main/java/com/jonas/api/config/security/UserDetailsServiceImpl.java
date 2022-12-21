package com.jonas.api.config.security;

import com.jonas.api.domain.Role;
import com.jonas.api.domain.User;
import com.jonas.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("Usuário do e-mail: "+ email+ " não encontrado!"));
        return new  User(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles());
    }
}
