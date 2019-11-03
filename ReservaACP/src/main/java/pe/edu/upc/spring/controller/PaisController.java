package pe.edu.upc.spring.controller;
import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pe.edu.upc.spring.model.Pais;
import pe.edu.upc.spring.service.IPaisService;

@Controller
@RequestMapping("/pais")
public class PaisController {
	@Autowired
	private IPaisService pService;
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPaises", pService.listar());
		return "listPaises";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("pais", new Pais());
		return "pais";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Pais objPais, 
			BindingResult binRes, Model model) throws ParseException {
		 if (binRes.hasErrors()) {
			 return "pais";
		 }
		 else {
			 boolean flag = pService.insertar(objPais);
			 if (flag)
				 return "redirect:/pais/listar";
			 else
			 {
				 model.addAttribute("mensaje","ocurrio un roche");
				 return "redirect:/pais/irRegistrar";
			 }
		 }
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Pais objPais, 
			BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException {
		 if (binRes.hasErrors()) {
			 return "redirect://pais/listar";
		 }
		 else 
		 {
			 boolean flag = pService.modificar(objPais);
			 if (flag) 
			 {
				 objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				 return "redirect:/pais/listar";
			 }			 
			 else
			 {
				 objRedir.addFlashAttribute("mensaje","ocurrio un roche");
				 return "redirect:/pais/irRegistrar";
			 }
		 }
	}	
		
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, 
			RedirectAttributes objRedir) throws ParseException {
		 
		Optional<Pais> objPais = pService.listarId(id);
		if (objPais == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error al cargar");
			return "redirect:/pais/listar";
		}
		else {
			model.addAttribute("pais", objPais);
			return "pais";
		}				
	}	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaPaises", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un gran rochetov");
			model.put("listaPaises", pService.listar());
		}
		return "listPaises";
	}
		
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Pais pais) {
		pService.listarId(pais.getIdPais());
		return "listPaises";
	}
	
}
