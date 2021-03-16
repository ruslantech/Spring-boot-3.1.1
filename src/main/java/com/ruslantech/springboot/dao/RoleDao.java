package com.ruslantech.springboot.dao;

import com.ruslantech.springboot.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getRoles();

    Role getRoleById(Long id);

}
