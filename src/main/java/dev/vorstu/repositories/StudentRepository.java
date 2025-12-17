package dev.vorstu.repositories;

import dev.vorstu.entities.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository  extends PagingAndSortingRepository<StudentEntity, Long>, CrudRepository<StudentEntity, Long>  {

    // TODO возвращает дубликаты почему-то(filter=ит)(посмотреть DISTINCT)
    @Query("SELECT s FROM StudentEntity s WHERE " +
            "UPPER(s.surname) LIKE UPPER(CONCAT('%', :filter, '%')) OR " +
            "UPPER(s.name) LIKE UPPER(CONCAT('%', :filter, '%')) OR " +
            "UPPER(s.patronymic) LIKE UPPER(CONCAT('%', :filter, '%')) OR " +
            "UPPER(s.group.nameOfGroup) LIKE UPPER(CONCAT('%', :filter, '%'))")
    Page<StudentEntity> getFilteredStudents(@Param("filter") String filter, Pageable pageable);

    @Query("SELECT COUNT(e) > 0 FROM StudentEntity e WHERE e.linkedCredentials.id = :credentialsId")
    boolean isUserExist(Long credentialsId);

    @Query("SELECT e FROM StudentEntity e WHERE e.linkedCredentials.id = :credentialsId")
    Optional<StudentEntity> getStudentByCredentials(Long credentialsId);
}
