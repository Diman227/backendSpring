package dev.vorstu.services.auth;

import dev.vorstu.dto.AuthUser;
import dev.vorstu.dto.auth.SignInRequest;
import dev.vorstu.entities.PasswordEntity;
import dev.vorstu.entities.StudentEntity;
import dev.vorstu.entities.UserEntity;
import dev.vorstu.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
                .enabled(user.isEnabled())
                .build();
    }

    // TODO на этом этапе уже должен привязываться к учетным данным какой-то пользователь с определенной ролью
    // TODO но как это сделать - ???, создать один супер-класс - ? || в запросе при создании уже как-то указывать роль
    // TODO на каком этапе вообще происходит присваивание пользователю какой-то из ролей
    public void createUser(SignInRequest request) {

        UserEntity userEntity = UserEntity.builder()
                .username(request.getUsername())
                .passwordEntity(new PasswordEntity(request.getPassword()))
                .enabled(true)
                .role(request.getRole())
                .build();

        userRepository.save(userEntity);
    }

}
