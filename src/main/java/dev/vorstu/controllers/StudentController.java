package dev.vorstu.controllers;

import dev.vorstu.dto.StudentDTO;
import dev.vorstu.entities.StudentEntity;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.services.StudentService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.webauthn.api.AuthenticatorResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("*api/base/")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Validated
public class StudentController {

    private final StudentService studentService;

    @PostMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO addStudent(@RequestBody StudentDTO newStudent) {
        return studentService.addStudent(newStudent);
    }

    @GetMapping(value = "student/group", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO getStudent(Long studentId){
        return studentService.getStudentById(studentId);
    }

    @GetMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<StudentDTO> loadStudentsPage(Pageable pageable) {
        return studentService.getStudentsPerPage(pageable);
    }

    @PatchMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO updateStudent(@RequestBody StudentDTO changingStudent) {return studentService.updateStudent(changingStudent);}

    @DeleteMapping(value = "students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long deleteStudent(@PathVariable("id") Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping(value = "students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO getStudentById(@PathVariable("id") @NotNull @Min(1) Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping(value = "students/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<StudentDTO> getFilteredStudents(@RequestParam(defaultValue = "", required = false) String filter, Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Username: " + authentication.getName());
        System.out.println("Authorities: " + authentication.getAuthorities());
        System.out.println("Principal: " + authentication.getPrincipal());
        System.out.println("Class: " + authentication.getClass());
        return studentService.getFilteredStudents(filter, pageable);
    }

}
