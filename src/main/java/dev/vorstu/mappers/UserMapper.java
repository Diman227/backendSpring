package dev.vorstu.mappers;

import dev.vorstu.dto.UserDTO;
import dev.vorstu.entities.GroupEntity;
import dev.vorstu.entities.StudentEntity;
import dev.vorstu.entities.TeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "groupId", source = "group.id")
    UserDTO StudentEntityToUserDTO(StudentEntity studentEntity);

    @Mapping(target = "groupId", source = "groupsOfStudents")
    UserDTO TeacherEntityToUserDTO(TeacherEntity teacherEntity);

    default Long map(List<GroupEntity> groups) {
        if (groups != null && !groups.isEmpty()) {
            return groups.get(0).getId();
        }
        return null;
    }
}
