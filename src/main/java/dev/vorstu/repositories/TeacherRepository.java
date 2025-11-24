package dev.vorstu.repositories;

import dev.vorstu.entities.TeacherEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherRepository extends CrudRepository<TeacherEntity, Long> {

}
