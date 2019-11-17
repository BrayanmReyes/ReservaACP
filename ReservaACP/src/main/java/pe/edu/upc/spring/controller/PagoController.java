package pe.edu.upc.spring.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/pago")
public class PagoController {
	@RequestMapping("/ir")
	public String irRegistrar() {
		//model.addAttribute("pais", new Pais());
		return "pago";
	}
	@RequestMapping("/exitoso")
	public String irExitoso() {
		//model.addAttribute("pais", new Pais());
		return "pagoexitoso";
	}
	@RequestMapping("/pagoqr")
	public String irQR() {
		//model.addAttribute("pais", new Pais());
		return "pagoQR";
	}
}
