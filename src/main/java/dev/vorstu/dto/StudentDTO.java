package dev.vorstu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private Long groupId;

    private Long credentialId;
}
