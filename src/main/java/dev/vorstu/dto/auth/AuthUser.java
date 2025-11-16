package dev.vorstu.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Array;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class AuthUser implements UserDetails {

     private String password;
     private String username;
     private boolean authorized;
     private List<SimpleGrantedAuthority> authorities;
}
