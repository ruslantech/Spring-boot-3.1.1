package com.ruslantech.springboot.dao;

import com.ruslantech.springboot.model.User;

import java.util.List;

public interface UserDao {

   void add(User user);

   List<User> listUsers();

   void remove(Long id);

   void update(User user);

   User getUserById(Long id);

   User getUserByFirstName(String name);

   boolean checkUserById(Long id);

}
