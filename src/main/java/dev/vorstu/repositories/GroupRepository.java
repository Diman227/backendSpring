package dev.vorstu.repositories;

import dev.vorstu.entities.GroupEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
    @Query("SELECT g FROM GroupEntity g WHERE " +
            "g.nameOfGroup = :groupName")
    Optional<GroupEntity> getGroupByName(@Param("groupName") String groupName);

    @Query("SELECT g FROM GroupEntity g WHERE g.id IN :ids")
    List<GroupEntity> getGroupsByIds(@Param("ids") List<Long> ids);
}
