package bm.com.spring_mvc_boot.config;

import bm.com.spring_mvc_boot.security.RoleLoginSuccessHandler;
import bm.com.spring_mvc_boot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    private final UserServiceImpl userServiceImpl;
    private final RoleLoginSuccessHandler handler;

    @Autowired
    public SpringSecurity(UserServiceImpl userServiceImpl, RoleLoginSuccessHandler hundler) {
        this.userServiceImpl = userServiceImpl;
        this.handler = hundler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(autmz -> autmz
                        .requestMatchers("/admin", "/admin/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/user", "/user/**").hasAuthority("ROLE_USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .successHandler(handler)
                )
                .userDetailsService(userServiceImpl)
                .logout(logt -> logt
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
