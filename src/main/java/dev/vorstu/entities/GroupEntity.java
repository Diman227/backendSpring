package dev.vorstu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "groups")
public class GroupEntity {

    public GroupEntity(String nameOfGroup, TeacherEntity groupTeacher, List<StudentEntity> students) {
        this.nameOfGroup = nameOfGroup;
        this.groupTeacher = groupTeacher;
        this.students = students;
    }

    public GroupEntity(String nameOfGroup, TeacherEntity groupTeacher) {
        this.nameOfGroup = nameOfGroup;
        this.groupTeacher = groupTeacher;
        this.students = new ArrayList<>();
    }

    public GroupEntity(String nameOfGroup) {
        this.nameOfGroup = nameOfGroup;
        this.groupTeacher = null;
        this.students = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id")
    private Long id;

    private String nameOfGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private TeacherEntity groupTeacher;

    @OneToMany(mappedBy = "group")
    private List<StudentEntity> students;
}
