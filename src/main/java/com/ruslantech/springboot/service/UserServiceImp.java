package com.ruslantech.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruslantech.springboot.dao.RoleDao;
import com.ruslantech.springboot.dao.UserDao;
import com.ruslantech.springboot.model.Role;
import com.ruslantech.springboot.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImp implements UserService {

   private UserDao userDao;
   private RoleDao roleDao;
   private PasswordEncoder passwordEncoder;

   @Autowired
   public UserServiceImp(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
      this.userDao = userDao;
      this.roleDao = roleDao;
      this.passwordEncoder = passwordEncoder;
   }

   @Override
   public void add(User user, Long[] rolesId) {
      HashSet<Role> roles = new HashSet<>();

      for(Long id: rolesId) {
         roles.add(roleDao.getRoleById(id));
      }

      user.setRoles(roles);
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userDao.add(user);
   }

   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Override
   public void remove(Long id) {
      userDao.remove(id);
   }

   @Override
   public void update(User user, Long[] rolesId) {
      Set<Role> roles = new HashSet<>();
      User oldUser = userDao.getUserById(user.getId());

      if (rolesId != null) {
         for (Long id : rolesId) {
            roles.add(roleDao.getRoleById(id));
         }
      } else {
         roles = oldUser.getRoles();
      }

      user.setPassword(oldUser.getPassword());
      user.setRoles(roles);
      userDao.update(user);
   }

   @Override
   public User getUserById(Long id) {
      return userDao.getUserById(id);
   }

   @Override
   public User getUserByFirstName(String name) {
        return userDao.getUserByFirstName(name);
    }

   @Override
   public boolean checkUserById(Long id) {
      return userDao.checkUserById(id);
   }
}
