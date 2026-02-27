package bm.com.spring_mvc_boot.service;

import bm.com.spring_mvc_boot.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleByName(String roleName);
}
