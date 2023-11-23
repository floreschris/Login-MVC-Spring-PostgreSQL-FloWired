package com.flowired.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.flowired.Service.UsuarioService;
import com.flowired.modelo.Usuario;

import org.springframework.ui.Model;

@Controller
public class UsuarioControler {

	private final UsuarioService usuarioService;

	public UsuarioControler(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("registerRequest", new Usuario());
		return "register_page";
	}

	@GetMapping("/login")
	public String getLoginPage(Model model) {
		model.addAttribute("loginRequest", new Usuario());
		return "login_page";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute Usuario usuario) {
		System.out.println("Register request: " + usuario);
		Usuario registeredUser = usuarioService.registraUsuario(usuario.getLogin(), usuario.getPassword(),
				usuario.getEmail());
		return registeredUser == null ? "error_page" : "redirect:/login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute Usuario usuario, Model model) {
		System.out.println("Login request: " + usuario);
		Usuario authenticated = usuarioService.authenticate(usuario.getLogin(), usuario.getPassword());

		if (authenticated != null) {
			model.addAttribute("userLogin", authenticated.getLogin());
			return "personal_page";
		} else {
			return "error_page";
		}
	}
}
