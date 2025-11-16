package dev.vorstu.services.auth;

import dev.vorstu.dto.auth.AuthUser;
import dev.vorstu.dto.auth.Role;
import dev.vorstu.dto.auth.SignInRequest;
import dev.vorstu.entities.PasswordEntity;
import dev.vorstu.entities.UserEntity;
import dev.vorstu.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User %s not found", username))
        );

        return AuthUser.builder()
                .username(user.getUsername())
                .password(user.getPasswordEntity().getPassword())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole().name())))
                .authorized(user.isAuthorized())
                .build();
    }

    public void createUser(SignInRequest request) {

        UserEntity entity = UserEntity.builder()
                .username(request.getUsername())
                .passwordEntity(new PasswordEntity(request.getPassword()))
                .authorized(true)
                .role(Role.STUDENT)
                .build();

        userRepository.save(entity);
    }

}
