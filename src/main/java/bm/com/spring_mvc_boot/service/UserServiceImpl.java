package bm.com.spring_mvc_boot.service;

import bm.com.spring_mvc_boot.model.User;
import bm.com.spring_mvc_boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void saveUser(User user, String roleName) {
        user.getRoles().add(roleService.getRoleByName(roleName));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateUser(Long id, User updatedUser, String roleName) {
        updatedUser.setId(id);

        if (updatedUser.getPassword().isEmpty()) {
            updatedUser.setPassword(findById(id).getPassword());
        } else {
            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        if (roleName == null) {
            updatedUser.setRoles(findById(id).getRoles());
        } else {
            updatedUser.getRoles().clear();
            updatedUser.getRoles().add(roleService.getRoleByName(roleName));
        }

        userRepository.save(updatedUser);
    }
}
