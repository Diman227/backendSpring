package dev.vorstu.dto.auth;

import dev.vorstu.dto.GroupNameDTO;
import dev.vorstu.dto.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SignInRequest {

    private String username;
    private String password;
    private String surname;
    private String name;
    private String patronymic;
    private Long groupId;
    private Role role;
}
