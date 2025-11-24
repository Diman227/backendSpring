package dev.vorstu.dto;

import dev.vorstu.entities.TeacherEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    private Long id;
    private String nameOfGroup;
    private TeacherEntity teacherEntity;
    private List<StudentDTO> students;
}
