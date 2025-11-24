package dev.vorstu.controllers;

import dev.vorstu.dto.StudentDTO;
import dev.vorstu.entities.StudentEntity;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("*api/base/")
@CrossOrigin(origins = "http://localhost:4200")
//@RequiredArgsConstructor
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public void addStudent(@RequestBody StudentDTO newStudent) {
        studentService.addStudent(newStudent);
    }

    @GetMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('TEACHER')")
    public Page<StudentDTO> loadStudentsPage(Pageable pageable) {
        return studentService.getStudentsPerPage(pageable);
    }

    @PatchMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO updateStudent(@RequestBody StudentDTO changingStudent) {
        return studentService.updateStudent(changingStudent);
    }

    @DeleteMapping(value = "students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long deleteStudent(@PathVariable("id") Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping(value = "students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO getStudentById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    // можно попробовать сделать все в один маппинг, но при каждом получении всех студентов будут отправляться запросы с пустым фильтром в бд
    @GetMapping(value = "students/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<StudentDTO> getFilteredStudents(@RequestParam(defaultValue = "", required = false) String filter, Pageable pageable) {
      return studentService.getFilteredStudents(filter, pageable);
    }

}
