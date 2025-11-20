package dev.vorstu.repositories;

import dev.vorstu.entities.TeacherEntity;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<TeacherEntity, Long> {
}
