package dev.vorstu.repositories;

import dev.vorstu.entities.CredentialsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CredentialsRepository extends CrudRepository<CredentialsEntity, Long> {

    @Query("SELECT u FROM CredentialsEntity u JOIN FETCH u.passwordEntity WHERE u.username = :name")
    Optional<CredentialsEntity> findByUsername(String name);
}
