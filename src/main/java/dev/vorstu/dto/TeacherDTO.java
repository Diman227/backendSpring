package dev.vorstu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private List<GroupNameDTO> groupsOfStudents;

}
