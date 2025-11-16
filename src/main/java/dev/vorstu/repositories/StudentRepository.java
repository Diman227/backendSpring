package dev.vorstu.repositories;

import dev.vorstu.entities.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository  extends PagingAndSortingRepository<StudentEntity, Long>, CrudRepository<StudentEntity, Long>  {

    @Query("SELECT s FROM StudentEntity s WHERE " +
            "UPPER(s.surname) LIKE UPPER(CONCAT('%', :filter, '%')) OR " +
            "UPPER(s.name) LIKE UPPER(CONCAT('%', :filter, '%')) OR " +
            "UPPER(s.patronymic) LIKE UPPER(CONCAT('%', :filter, '%')) OR " +
            "UPPER(s.group) LIKE UPPER(CONCAT('%', :filter, '%')) OR " +
            "UPPER(s.phoneNumber) LIKE UPPER(CONCAT('%', :filter, '%'))")
    Page<StudentEntity> getFilteredStudents(@Param("filter") String filter, Pageable pageable);
}
