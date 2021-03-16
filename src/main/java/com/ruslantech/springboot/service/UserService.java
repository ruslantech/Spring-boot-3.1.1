package com.ruslantech.springboot.service;

import com.ruslantech.springboot.model.User;

import java.util.List;

public interface UserService {

    void add(User user, Long[] rolesId);

    List<User> listUsers();

    void remove(Long id);

    void update(User user, Long[] rolesId);

    User getUserById(Long id);

    User getUserByFirstName(String name);

    boolean checkUserById(Long id);
}
