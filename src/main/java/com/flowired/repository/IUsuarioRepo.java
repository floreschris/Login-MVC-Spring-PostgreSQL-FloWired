package com.flowired.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowired.modelo.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByLoginAndPassword(String login, String password);

	Optional<Usuario> findFirstByLogin(String login);
}
