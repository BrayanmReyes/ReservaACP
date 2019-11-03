package pe.edu.upc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/landing")
public class LandingController {
	@RequestMapping("/mostrar")
	public String mostar() {
		return "ACP";
	}

	@RequestMapping("/prueba")
	public String listar() {
		return "1prueba";
	}
}
