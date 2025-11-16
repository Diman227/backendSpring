package dev.vorstu.controllers;

import dev.vorstu.dto.auth.SignInRequest;
import dev.vorstu.dto.auth.TokenRequest;
import dev.vorstu.dto.auth.TokenResponse;
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

    @PostMapping("login")
    public TokenResponse createToken(@RequestBody TokenRequest tokenRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(tokenRequest.getUsername(), tokenRequest.getPassword())
            );
        }
        catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
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
}
