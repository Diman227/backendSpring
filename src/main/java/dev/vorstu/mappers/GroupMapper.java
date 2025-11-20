package dev.vorstu.mappers;

import dev.vorstu.dto.GroupDTO;
import dev.vorstu.entities.GroupEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupDTO toGroupDTO(GroupEntity groupEntity);

    GroupEntity toGroupEntity(GroupDTO groupDTO);

}
