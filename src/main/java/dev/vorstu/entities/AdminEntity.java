package dev.vorstu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admins")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminEntity {

    public AdminEntity(String surname, String patronymic, String name) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_id")
    private Long id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

}
