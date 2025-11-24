package dev.vorstu.mappers;

import dev.vorstu.dto.TeacherDTO;
import dev.vorstu.entities.TeacherEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherDTO toTeacherDTO(TeacherEntity teacherEntity);

    TeacherEntity toTeacherEntity(TeacherDTO teacherDTO);
}
