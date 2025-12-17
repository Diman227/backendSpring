package dev.vorstu.repositories;

import dev.vorstu.entities.GroupEntity;
import dev.vorstu.entities.StudentEntity;
import dev.vorstu.entities.TeacherEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends CrudRepository<TeacherEntity, Long> {

    @Query("SELECT COUNT(e) > 0 FROM TeacherEntity e WHERE e.linkedCredentials.id = :credentialsId")
    boolean isUserExist(Long credentialsId);

    @Query("SELECT e FROM TeacherEntity e WHERE e.linkedCredentials.id = :credentialsId")
    Optional<TeacherEntity> getTeacherByCredentials(Long credentialsId);

    @Query("SELECT g FROM GroupEntity g WHERE groupTeacher.id = :teacherId")
    Optional<List<GroupEntity>> getTeacherGroups(Long teacherId);
}
