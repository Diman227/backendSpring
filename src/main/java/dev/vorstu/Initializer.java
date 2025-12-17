package dev.vorstu;

import dev.vorstu.dto.Role;
import dev.vorstu.dto.auth.SignInRequest;
import dev.vorstu.entities.GroupEntity;
import dev.vorstu.repositories.GroupRepository;
import dev.vorstu.services.auth.AuthUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Initializer {

    private final GroupRepository groupRepository;
    private final AuthUserDetailsService authUserDetailsService;

    public void initial(){

        GroupEntity group1 = groupRepository.save(new GroupEntity("бВМ-211"));
        GroupEntity group2 = groupRepository.save(new GroupEntity("бВМ-212"));
        GroupEntity group3 = groupRepository.save(new GroupEntity("бВМ-213"));
        GroupEntity group4 = groupRepository.save(new GroupEntity("бВМ-221"));
        GroupEntity group5 = groupRepository.save(new GroupEntity("бВМ-222"));
        GroupEntity group6 = groupRepository.save(new GroupEntity("бВМ-231"));
        GroupEntity group7 = groupRepository.save(new GroupEntity("бВМ-232"));

        authUserDetailsService.createUser(new SignInRequest("student1", "student", "Никитин", "Никита", "Никитыч", group1.getId(), Role.STUDENT));
        authUserDetailsService.createUser(new SignInRequest("student2", "student", "Иванов", "Иван", "Иванович", group2.getId(), Role.STUDENT));
        authUserDetailsService.createUser(new SignInRequest("student3", "student", "Сергеев", "Сергей", "Сергеевич", group3.getId(), Role.STUDENT));
        authUserDetailsService.createUser(new SignInRequest("student4", "student", "Васильев", "Василий", "Васильевич", group4.getId(), Role.STUDENT));
        authUserDetailsService.createUser(new SignInRequest("student5", "student", "Борисов", "Борис", "Борисович", group1.getId(), Role.STUDENT));
        authUserDetailsService.createUser(new SignInRequest("student6", "student", "Алексеев", "Алексей", "Алексеевич", group1.getId(), Role.STUDENT));

        authUserDetailsService.createUser(new SignInRequest("teacher1", "teacher", "Павлова", "Лидия", "Лидия", group1.getId(), Role.TEACHER));
        authUserDetailsService.createUser(new SignInRequest("teacher2", "teacher", "Петрова", "Надежда", "Викторовна", group2.getId(), Role.TEACHER));
        authUserDetailsService.createUser(new SignInRequest("teacher3", "teacher", "Молодцова", "Наталья", "Алексеевна", group3.getId(), Role.TEACHER));
        authUserDetailsService.createUser(new SignInRequest("teacher4", "teacher", "Иванов", "Павел", "Никитыч", group5.getId(), Role.TEACHER));
        authUserDetailsService.createUser(new SignInRequest("teacher5", "teacher", "Палкин", "Василий", "Александрович", group5.getId(), Role.TEACHER));

        authUserDetailsService.createUser(new SignInRequest("admin", "admin", null, null, null, null, Role.ADMIN));
    }
}
