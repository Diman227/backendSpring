package dev.vorstu.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherEntity {

    public TeacherEntity(String surname, String patronymic, String name, ArrayList<GroupEntity> groupsOfStudents) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.groupsOfStudents = groupsOfStudents;
    }

    public TeacherEntity(String surname, String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "teacher_id")
    private Long id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "groupTeacher")
    private List<GroupEntity> groupsOfStudents;
}
