package dev.vorstu.mappers;

import dev.vorstu.dto.GroupDTO;
import dev.vorstu.dto.GroupNameDTO;
import dev.vorstu.entities.GroupEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupDTO toGroupDTO(GroupEntity groupEntity);

    GroupEntity toGroupEntity(GroupDTO groupDTO);

    GroupNameDTO toGroupNameDTO(GroupEntity groupEntity);

    List<GroupNameDTO> toListGroupNameDTOs(List<GroupEntity> listOfGroupEntities);

}
