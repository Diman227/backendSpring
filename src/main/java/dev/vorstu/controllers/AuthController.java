package dev.vorstu.controllers;

import dev.vorstu.dto.UserDTO;
import dev.vorstu.dto.auth.SignInRequest;
import dev.vorstu.dto.auth.TokenRequest;
import dev.vorstu.dto.auth.TokenResponse;
import dev.vorstu.mappers.UserMapper;
import dev.vorstu.repositories.CredentialsRepository;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.repositories.TeacherRepository;
import dev.vorstu.services.auth.AuthUserDetailsService;
import dev.vorstu.services.auth.TokenManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("*api/")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;
    private final CredentialsRepository credentialsRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final UserMapper userMapper;

    @PostMapping("login")
    public TokenResponse createToken(@RequestBody TokenRequest tokenRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(tokenRequest.getUsername(), tokenRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(tokenRequest.getUsername());
        return new TokenResponse(tokenManager.generateJwtToken(userDetails));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "register")
    public void addUser(@RequestBody SignInRequest request) {

        userDetailsService.createUser(request);
    }

    @GetMapping(value = "{username}")
    public Long getIdByUsername(@PathVariable("username") String username) {
        return credentialsRepository.findIdByUsername(username);
    }

    @GetMapping(value = "{username}/getUser")
    public UserDTO getUserByHisLinkedCredentialsId(@PathVariable("username") String username) {
        Long credentialsId = this.credentialsRepository.findIdByUsername(username);
        if(this.studentRepository.isUserExist(credentialsId)) {
            return this.userMapper.StudentEntityToUserDTO(this.studentRepository.getStudentByCredentials(credentialsId).orElseThrow());
        }
        if(this.teacherRepository.isUserExist(credentialsId)) {
            return this.userMapper.TeacherEntityToUserDTO(this.teacherRepository.getTeacherByCredentials(credentialsId).orElseThrow());
        }
        else {
            return  null;
        }
    }
}
