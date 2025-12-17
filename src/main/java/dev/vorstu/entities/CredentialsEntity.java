package dev.vorstu.entities;

import dev.vorstu.dto.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "credentials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CredentialsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "credentials_id")
    private Long id;

    @Column(unique = true)
    private String username;

    private boolean enabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "password_id", nullable = false)
    private PasswordEntity passwordEntity;

    //todo пришел к тому, как будто нужно было оставлять тут айди на сущности ролей,
    // теперь не понимаю как из credentials получить определенного человека
}
