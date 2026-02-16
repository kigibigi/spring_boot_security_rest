package bm.com.spring_mvc_boot.service;

import bm.com.spring_mvc_boot.model.Role;

import java.util.List;

public interface RoleService {
    Role getRoleUser();
    List<Role> getAllRoles();
//    String getNameRole(Role role);
    Role getRoleByName(String roleName);
}
