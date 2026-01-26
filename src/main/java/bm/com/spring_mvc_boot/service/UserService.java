package bm.com.spring_mvc_boot.service;


import bm.com.spring_mvc_boot.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> findAll();
    User findById(Long id);
    void delete(Long id);
    void update(Long id, User user);
}
