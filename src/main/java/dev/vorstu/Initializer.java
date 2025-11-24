package dev.vorstu;

import dev.vorstu.dto.Role;
import dev.vorstu.dto.auth.SignInRequest;
import dev.vorstu.entities.GroupEntity;
import dev.vorstu.entities.StudentEntity;
import dev.vorstu.entities.TeacherEntity;
import dev.vorstu.mappers.GroupMapper;
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

    @Autowired
    private GroupMapper groupMapper;

    public void initial(){

        TeacherEntity teacher1 = new TeacherEntity("Павлова", "Лидия", "Павловловна");
        TeacherEntity teacher2 = new TeacherEntity("Петрова", "Надежда", "Викторовна");
        TeacherEntity teacher3 = new TeacherEntity("Молодцова", "Наталья", "Алексеевна");
        TeacherEntity teacher4 = new TeacherEntity("Иванов", "Павел", "Никитыч");
        TeacherEntity teacher5 = new TeacherEntity("Палкин", "Василий", "Александрович");

        GroupEntity group1 = groupRepository.save(new GroupEntity("бВМ-211", teacher1));
        GroupEntity group2 = groupRepository.save(new GroupEntity("бВМ-212", teacher2));
        GroupEntity group3 = groupRepository.save(new GroupEntity("бВМ-213", teacher3));
        GroupEntity group4 = groupRepository.save(new GroupEntity("бВМ-221", teacher5));
        GroupEntity group5 = groupRepository.save(new GroupEntity("бВМ-222", teacher5));
        GroupEntity group6 = groupRepository.save(new GroupEntity("бВМ-231"));
        GroupEntity group7 = groupRepository.save(new GroupEntity("бВМ-232"));

        StudentEntity student1 = new StudentEntity("Никитин", "Никита", "Никитыч", group1);
        StudentEntity student2 = new StudentEntity("Иванов", "Иван", "Иванович", group2);
        StudentEntity student3 = new StudentEntity("Сергеев", "Сергей", "Сергеевич", group3);
        StudentEntity student4 = new StudentEntity("Васильев", "Василий", "Васильевич", group4);
        StudentEntity student5 = new StudentEntity("Борисов", "Борис", "Борисович", group1);
        StudentEntity student6 = new StudentEntity("Алексеев", "Алексей", "Алексеевич", group7);

        authUserDetailsService.createUser(new SignInRequest("student1", "student", student1.getSurname(), student1.getName(), student1.getPatronymic(), group1.getId(), Role.STUDENT));
        authUserDetailsService.createUser(new SignInRequest("student2", "student", student2.getSurname(), student2.getName(), student2.getPatronymic(), group2.getId(), Role.STUDENT));
        authUserDetailsService.createUser(new SignInRequest("student3", "student", student3.getSurname(), student3.getName(), student3.getPatronymic(), group3.getId(), Role.STUDENT));
        authUserDetailsService.createUser(new SignInRequest("student4", "student", student4.getSurname(), student4.getName(), student4.getPatronymic(), group4.getId(), Role.STUDENT));
        authUserDetailsService.createUser(new SignInRequest("student5", "student", student5.getSurname(), student5.getName(), student5.getPatronymic(), group1.getId(), Role.STUDENT));
        authUserDetailsService.createUser(new SignInRequest("student6", "student", student6.getSurname(), student6.getName(), student6.getPatronymic(), group1.getId(), Role.STUDENT));

        authUserDetailsService.createUser(new SignInRequest("teacher1", "teacher", teacher1.getSurname(), teacher1.getName(), teacher1.getPatronymic(), null, Role.TEACHER));
        authUserDetailsService.createUser(new SignInRequest("teacher2", "teacher", teacher2.getSurname(), teacher2.getName(), teacher2.getPatronymic(), null, Role.TEACHER));
        authUserDetailsService.createUser(new SignInRequest("teacher3", "teacher", teacher3.getSurname(), teacher3.getName(), teacher3.getPatronymic(), null, Role.TEACHER));
        authUserDetailsService.createUser(new SignInRequest("teacher4", "teacher", teacher4.getSurname(), teacher4.getName(), teacher4.getPatronymic(), null, Role.TEACHER));
        authUserDetailsService.createUser(new SignInRequest("teacher5", "teacher", teacher5.getSurname(), teacher5.getName(), teacher5.getPatronymic(), null, Role.TEACHER));

        authUserDetailsService.createUser(new SignInRequest("admin", "admin", null, null, null, null, Role.ADMIN));
    }
}
