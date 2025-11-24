package dev.vorstu.mappers;

import dev.vorstu.dto.StudentDTO;
import dev.vorstu.entities.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "groupName", source = "group.nameOfGroup")
    StudentDTO toStudentDTO(StudentEntity studentEntity);

    // Группа присваивается в сервисе
    @Mapping(target = "group", ignore = true)
    StudentEntity toStudentEntity(StudentDTO studentDTO);

    List<StudentDTO> toListStudentDTOs(List<StudentEntity> listOfStudentEntities);

    List<StudentEntity> toListStudentEntities(List<StudentDTO> listOfStudentDTOs);

    default Page<StudentDTO> toPageStudentDTO(Page<StudentEntity> studentEntityPage) {
        if (studentEntityPage == null){
            return Page.empty();
        }
        return studentEntityPage.map(this::toStudentDTO);
    };

    default Page<StudentEntity> toPageStudentEntity(Page<StudentDTO> studentDTOPage)
    {
        if (studentDTOPage == null){
            return Page.empty();
        }
        return studentDTOPage.map(this::toStudentEntity);
    };

}
