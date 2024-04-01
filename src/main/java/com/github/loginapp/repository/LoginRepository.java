package com.github.loginapp.repository;

import com.github.loginapp.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {

    Optional<Login> findByNome(String nome);

}
