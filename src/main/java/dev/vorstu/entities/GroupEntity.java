package dev.vorstu.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    //todo unique , handle this exception
    private String nameOfGroup;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
//    @JsonBackReference
    private TeacherEntity groupTeacher;

    @OneToMany(mappedBy = "group")
    private List<StudentEntity> students;
}
