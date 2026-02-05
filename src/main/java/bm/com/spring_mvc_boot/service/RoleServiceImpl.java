package bm.com.spring_mvc_boot.service;

import bm.com.spring_mvc_boot.model.Role;
import bm.com.spring_mvc_boot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleUser() {
        return roleRepository.findByRole("ROLE_USER").get();
    }
}
