package dev.vorstu.dto;

import dev.vorstu.entities.TeacherEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {

    @Positive
    @NotNull
    private Long id;

    @NotNull
    private String nameOfGroup;

    private Long groupTeacherId;

    private List<StudentDTO> students;

//    public interface Create {}
//    public interface Update {}
}
