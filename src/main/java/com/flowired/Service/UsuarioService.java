package com.flowired.Service;

import org.springframework.stereotype.Service;

import com.flowired.modelo.Usuario;
import com.flowired.repository.IUsuarioRepo;

@Service
public class UsuarioService {

	private final IUsuarioRepo IUsuarioRepo;

	public UsuarioService(IUsuarioRepo iUsuarioRepo) {
		this.IUsuarioRepo = iUsuarioRepo;
	}

	public Usuario registraUsuario(String login, String password, String email) {

		if (login == null || password == null) {
			return null;
		} else {

			if (IUsuarioRepo.findFirstByLogin(login).isPresent()) {
				System.out.println("Duplicate login");
				return null;
			}

			Usuario usuario = new Usuario();
			usuario.setLogin(login);
			usuario.setPassword(password);
			usuario.setEmail(email);

			return IUsuarioRepo.save(usuario);

		}

	}

	public Usuario authenticate(String login, String password) {

		return IUsuarioRepo.findByLoginAndPassword(login, password).orElse(null);

	}

}
