package dev.vorstu.mappers;

import dev.vorstu.dto.GroupDTO;
import dev.vorstu.dto.GroupNameDTO;
import dev.vorstu.dto.StudentDTO;
import dev.vorstu.entities.GroupEntity;
import dev.vorstu.entities.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {


    @Mapping(target = "groupTeacherId", source = "groupTeacher.id")
    GroupDTO toGroupDTO(GroupEntity groupEntity);

    GroupEntity toGroupEntity(GroupDTO groupDTO);

    GroupNameDTO toGroupNameDTO(GroupEntity groupEntity);

    List<GroupNameDTO> toListGroupNameDTOs(List<GroupEntity> listOfGroupEntities);


}
