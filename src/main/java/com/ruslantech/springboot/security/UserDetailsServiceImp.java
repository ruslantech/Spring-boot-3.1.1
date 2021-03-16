package com.ruslantech.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruslantech.springboot.dao.UserDao;
import com.ruslantech.springboot.model.User;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        User user = userDao.getUserByFirstName(firstName);

        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }
}
