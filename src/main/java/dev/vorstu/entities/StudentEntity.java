package dev.vorstu.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "students")
public class StudentEntity {

    public StudentEntity(String surname, String name, String patronymic, GroupEntity group) {

        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.group = group;
    }

    // TODO посмотреть чем GenerationType.AUTO отличается от IDENTITY, пишут, что второе лучше
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credentials_id")
    private CredentialsEntity linkedCredentials;
}
