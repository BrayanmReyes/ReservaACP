package pe.edu.upc.spring.controller;
import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private IUsuarioService pService;
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaUsuarios", pService.listar());
		return "listUsuarios";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuario";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Usuario objUsuario, 
			BindingResult binRes, Model model) throws ParseException {
		 if (binRes.hasErrors()) {
			 return "usuario";
		 }
		 else {
			 boolean flag = pService.insertar(objUsuario);
			 if (flag)
				 return "redirect:/usuario/listar";
			 else
			 {
				 model.addAttribute("mensaje","ocurrio un roche");
				 return "redirect:/usuario/irRegistrar";
			 }
		 }
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Usuario objUsuario, 
			BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException {
		 if (binRes.hasErrors()) {
			 return "redirect://usuario/listar";
		 }
		 else 
		 {
			 boolean flag = pService.modificar(objUsuario);
			 if (flag) 
			 {
				 objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				 return "redirect:/usuario/listar";
			 }			 
			 else
			 {
				 objRedir.addFlashAttribute("mensaje","ocurrio un roche");
				 return "redirect:/usuario/irRegistrar";
			 }
		 }
	}	
		
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, 
			RedirectAttributes objRedir) throws ParseException {
		 
		Optional<Usuario> objUsuario = pService.listarId(id);
		if (objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error al cargar");
			return "redirect:/usuario/listar";
		}
		else {
			model.addAttribute("usuario", objUsuario);
			return "usuario";
		}				
	}	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaUsuarios", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un gran rochetov");
			model.put("listaUsuarios", pService.listar());
		}
		return "listUsuarios";
	}
		
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Usuario usuario) {
		pService.listarId(usuario.getIdUsuario());
		return "listUsuarios";
	}

	@GetMapping("/buscar")
	public String buscarName(@RequestParam("email") String email,Map<String, Object> model) {
		model.put("listaUsuarios", pService.buscarUsuario(email.toLowerCase()));
		return "listUsuarios";
	}
}
