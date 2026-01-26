package bm.com.spring_mvc_boot.service;

import bm.com.spring_mvc_boot.model.User;
import bm.com.spring_mvc_boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        // совпало что название которое мне предложено идеешкой, в интерфейсе
        // JpaRepository метод для всех людей называется так же
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        // можно сделать обрабоотку какого либо исключения если будет выброшен null
        // но это так размышления и то не мои
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void update(Long id, User updatedUser) {
        updatedUser.setId(id);
        // соглашение что и для сохранени и для изменения используется метод save
        // но поскольку уже такой id используется он просто изменит значения
        userRepository.save(updatedUser);
    }
}
