package bm.com.spring_mvc_boot.service;


import bm.com.spring_mvc_boot.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user, String roleName);
    List<User> findAll();
    User findById(Long id);
    void deleteUser(Long id);
    void updateUser(Long id, User user, String roleName);
}
