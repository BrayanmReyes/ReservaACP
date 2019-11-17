package pe.edu.upc.spring.controller;
import java.text.ParseException;
//import java.util.Map;
//import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pe.edu.upc.spring.model.Users;
import pe.edu.upc.spring.service.IUsersService;

@Controller
@RequestMapping("/cuenta")
public class UsersController {
	@Autowired
	private IUsersService pService;
	@Autowired
	private BCryptPasswordEncoder passwordCodifica;

	@Bean
	public BCryptPasswordEncoder passwordCodifica() {
		return new BCryptPasswordEncoder();
	}

	@RequestMapping("/irPassword")
	public String irPassword(Model model) {
		model.addAttribute("user", new Users());
		return "password";
	}
	
	
	@GetMapping("/password")
	public String Generar(@RequestParam("password") String password,Model model,@ModelAttribute @Valid Users objUser) {
			//String password = "admin";
			String bcryptPassword = passwordCodifica.encode(password);
			System.out.println(bcryptPassword);
			
			
			model.addAttribute("user", new Users());
			model.addAttribute("user", objUser);
			model.addAttribute("bcryptPassword", objUser.getPassword());
		return "crearcuenta";
	}
	
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("user", new Users());
		return "user";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Users objUsuario, 
			BindingResult binRes, Model model) throws ParseException {
		 if (binRes.hasErrors()) {
			 return "user";
		 }
		 else {
			 boolean flag = pService.insertar(objUsuario);
			 if (flag)
				 return "redirect:/login";
			 else
			 {
				 model.addAttribute("mensaje","ocurrio un roche");
				 return "redirect:/error_403";
			 }
		 }
	}
}
