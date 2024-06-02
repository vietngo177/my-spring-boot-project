package vn.vietngo.spring.myproject.service;

import vn.vietngo.spring.myproject.entity.Role;

import java.util.List;

public interface RoleService {
    Role findByRole(String role);

    List<Role> getAllRole();
}
