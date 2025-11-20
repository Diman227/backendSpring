package dev.vorstu;

import dev.vorstu.dto.Role;
import dev.vorstu.dto.auth.SignInRequest;
import dev.vorstu.entities.GroupEntity;
import dev.vorstu.entities.StudentEntity;
import dev.vorstu.entities.TeacherEntity;
import dev.vorstu.repositories.GroupRepository;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.repositories.TeacherRepository;
import dev.vorstu.services.auth.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AuthUserDetailsService authUserDetailsService;

    public void initial(){

        authUserDetailsService.createUser(new SignInRequest("student", "student", Role.STUDENT));
        authUserDetailsService.createUser(new SignInRequest("teacher", "teacher", Role.TEACHER));
        authUserDetailsService.createUser(new SignInRequest("admin", "admin", Role.ADMIN));

        TeacherEntity teacher1 = teacherRepository.save(new TeacherEntity("Павлова", "Лидия", "Павловловна"));
        TeacherEntity teacher2 = teacherRepository.save(new TeacherEntity("Петрова", "Надежда", "Викторовна"));
        TeacherEntity teacher3 = teacherRepository.save(new TeacherEntity("Молодцова", "Наталья", "Алексеевна"));
        TeacherEntity teacher4 = teacherRepository.save(new TeacherEntity("Иванов", "Павел", "Никитыч"));
        TeacherEntity teacher5 = teacherRepository.save(new TeacherEntity("Палкин", "Василий", "Александрович"));

        GroupEntity group1 = groupRepository.save(new GroupEntity("бВМ-211", teacher1));
        GroupEntity group2 = groupRepository.save(new GroupEntity("бВМ-212", teacher2));
        GroupEntity group3 = groupRepository.save(new GroupEntity("бВМ-213", teacher3));
        GroupEntity group4 = groupRepository.save(new GroupEntity("бВМ-221", teacher5));
        GroupEntity group5 = groupRepository.save(new GroupEntity("бВМ-222", teacher5));
        GroupEntity group6 = groupRepository.save(new GroupEntity("бВМ-231"));
        GroupEntity group7 = groupRepository.save(new GroupEntity("бВМ-232"));

        studentRepository.save(new StudentEntity("Никитин", "Никита", "Никитыч", group1, "+79"));
        studentRepository.save(new StudentEntity("Иванов", "Иван", "Иванович", group2, "+791"));
        studentRepository.save(new StudentEntity("Сергеев", "Сергей", "Сергеевич", group3, "+792"));
        studentRepository.save(new StudentEntity("Васильев", "Василий", "Васильевич", group4, "+793"));
        studentRepository.save(new StudentEntity("Борисов", "Борис", "Борисович", group1, "+794"));
        studentRepository.save(new StudentEntity("Алексеев", "Алексей", "Алексеевич", group7, "+795"));
    }
}
