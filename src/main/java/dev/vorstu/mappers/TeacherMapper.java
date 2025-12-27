package dev.vorstu.mappers;

import dev.vorstu.dto.TeacherDTO;
import dev.vorstu.entities.TeacherEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = GroupMapper.class)
public interface TeacherMapper {

    TeacherDTO toTeacherDTO(TeacherEntity teacherEntity);

    TeacherEntity toTeacherEntity(TeacherDTO teacherDTO);

    List<TeacherDTO> toListTeacherDTO(List<TeacherEntity> teacherEntities);

    List<TeacherEntity> toListTeacherEntity(List<TeacherDTO> teacherDTOS);
}
