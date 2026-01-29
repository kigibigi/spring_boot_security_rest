package bm.com.spring_mvc_boot.security;

import bm.com.spring_mvc_boot.model.Role;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

public class RoleAuthorityImpl implements GrantedAuthority {

    private Role role;

    public RoleAuthorityImpl(Role role) {
        this.role = role;
    }

    @Override
    public @Nullable String getAuthority() {
        return this.role.getRole();
    }

}
