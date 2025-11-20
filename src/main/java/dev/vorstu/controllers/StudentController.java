package dev.vorstu.controllers;

import dev.vorstu.entities.StudentEntity;
import dev.vorstu.repositories.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("*api/base/")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentEntity addStudent(@RequestBody StudentEntity newStudent) {
        studentRepository.save(newStudent);
        return newStudent;
    }

//    @GetMapping("students")
//    public Iterable<Student> getAllStudents() {
//        return studentRepository.findAll();
//    }

    @PatchMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentEntity updateStudent(@RequestBody StudentEntity changingStudent) {
        studentRepository.save(changingStudent);
        return changingStudent;
    }

    @DeleteMapping(value = "students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long deleteStudent(@PathVariable("id") Long id) {
        studentRepository.deleteById(id);
        return id;
    }

    @GetMapping(value = "students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<StudentEntity> getStudentById(@PathVariable("id") Long id) {

        return studentRepository.findById(id);
    }

    @GetMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<StudentEntity> loadStudentsPage(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    // можно попробовать сделать все в один маппинг, но при каждом получении всех студентов будут отправляться запросы с пустым фильтром в бд
    @GetMapping(value = "students/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<StudentEntity> getFilteredStudents(@RequestParam(defaultValue = "", required = false) String filter, Pageable pageable) {
      return studentRepository.getFilteredStudents(filter, pageable);
    }

}
