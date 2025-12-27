package dev.vorstu.controllers;

import dev.vorstu.dto.GroupNameDTO;
import dev.vorstu.dto.TeacherDTO;
import dev.vorstu.services.TeacherService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("*api/base/")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping(value = "teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public TeacherDTO addTeacher(@RequestBody @Validated TeacherDTO teacherDTO) {
        return teacherService.addTeacher(teacherDTO);
    }

    @GetMapping(value = "teachers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeacherDTO getTeacherById(@PathVariable("id") @NotNull Long id){
        return teacherService.getTeacherById(id);
    }

    @DeleteMapping(value = "teachers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public Long deleteTeacher(@PathVariable("id") @NotNull Long id){
        return teacherService.deleteTeacher(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @PatchMapping(value = "teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeacherDTO updateTeacher(@RequestBody @Validated TeacherDTO teacherDTO) {
        return teacherService.updateTeacher(teacherDTO);
    }

    @GetMapping(value = "teachers/{id}/groups", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GroupNameDTO> getTeacherGroups(@PathVariable("id") @NotNull Long id) {
        return this.teacherService.getTeacherGroups(id);
    }

    @GetMapping(value = "teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeacherDTO> getAllTeachers() {
        return this.teacherService.getAllTeachers();
    }
}
