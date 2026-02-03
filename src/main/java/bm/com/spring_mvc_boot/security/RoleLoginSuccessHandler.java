package bm.com.spring_mvc_boot.security;

import bm.com.spring_mvc_boot.model.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Set<String> roles = userDetails.getUser().getRoles().stream()
                .map(Role::getRole)
                .collect(Collectors.toSet());

        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("admin");
            return;
        } else if (roles.contains("ROLE_USER")) {
            response.sendRedirect("user/myPage");
        }
    }
}
