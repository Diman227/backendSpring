package dev.vorstu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    @Positive
    private Long id;

    @NotNull
    private String surname;

    @NotNull
    private String name;

    @NotNull
    private String patronymic;

    @Positive
    @NotNull
    private Long groupId;
}
