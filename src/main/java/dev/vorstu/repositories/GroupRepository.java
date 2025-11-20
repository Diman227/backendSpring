package dev.vorstu.repositories;

import dev.vorstu.entities.GroupEntity;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
}
