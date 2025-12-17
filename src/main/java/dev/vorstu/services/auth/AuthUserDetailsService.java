package dev.vorstu.services.auth;

import dev.vorstu.dto.auth.AuthUser;
import dev.vorstu.dto.auth.SignInRequest;
import dev.vorstu.entities.*;
import dev.vorstu.repositories.GroupRepository;
import dev.vorstu.repositories.CredentialsRepository;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.repositories.TeacherRepository;
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

    private final CredentialsRepository credentialsRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CredentialsEntity user = credentialsRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User %s not found", username))
        );

        return AuthUser.builder()
                .username(user.getUsername())
                .password(user.getPasswordEntity().getPassword())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole().name())))
                .enabled(user.isEnabled())
                .build();
    }

    public void createUser(SignInRequest request) {

        CredentialsEntity credentialsEntity = CredentialsEntity.builder()
                .username(request.getUsername())
                .passwordEntity(new PasswordEntity(request.getPassword()))
                .enabled(true)
                .role(request.getRole())
                .build();

        switch (request.getRole().toString()) {

            case "STUDENT":
                GroupEntity groupEntity = groupRepository.findById(request.getGroupId()).orElseThrow();
                StudentEntity student = StudentEntity.builder()
                                .surname(request.getSurname())
                                .name(request.getName())
                                .patronymic(request.getPatronymic())
                                .group(groupEntity)
                                .linkedCredentials(credentialsEntity)
                                .build();

                studentRepository.save(student);
                break;

            case "TEACHER":
                TeacherEntity teacher = TeacherEntity.builder()
                                .surname(request.getSurname())
                                .name(request.getName())
                                .patronymic(request.getPatronymic())
                                .linkedCredentials(credentialsEntity)
                                .build();

                teacherRepository.save(teacher);
                break;
        }

        credentialsRepository.save(credentialsEntity);
    }

}
