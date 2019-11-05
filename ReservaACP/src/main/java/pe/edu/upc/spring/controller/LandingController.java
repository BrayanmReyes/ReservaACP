package pe.edu.upc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/landing")
public class LandingController {
	@RequestMapping("/bienvenido")
	public String Bienvenido() {
		return "bienvenido";
	}
}
