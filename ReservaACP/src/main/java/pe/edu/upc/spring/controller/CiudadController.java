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

import pe.edu.upc.spring.model.Ciudad;
import pe.edu.upc.spring.model.Pais;
import pe.edu.upc.spring.service.ICiudadService;
import pe.edu.upc.spring.service.IPaisService;

@Controller
@RequestMapping("/ciudad")
public class CiudadController {
	@Autowired
	private ICiudadService pService;
	@Autowired
	private IPaisService cService;
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaCiudades", pService.listar());
		model.put("listaPaises",cService.listar());
		return "listCiudades";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("ciudad", new Ciudad());
		model.addAttribute("listaPaises", cService.listar());
		return "ciudad";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Ciudad objCiudad, 
			BindingResult binRes, Model model) throws ParseException {
		 if (binRes.hasErrors()) {
			 model.addAttribute("listaPaises", cService.listar());
			 return "ciudad";
		 }
		 else {
			 boolean flag = pService.insertar(objCiudad);
			 if (flag)
				 return "redirect:/ciudad/listar";
			 else
			 {
				 model.addAttribute("mensaje","ocurrio un roche");
				 return "redirect:/ciudad/irRegistrar";
			 }
		 }
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Ciudad objCiudad, 
			BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException {
		 if (binRes.hasErrors()) {
			 return "redirect://ciudad/listar";
		 }
		 else 
		 {
			 boolean flag = pService.modificar(objCiudad);
			 if (flag) 
			 {
				 objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				 return "redirect:/ciudad/listar";
			 }			 
			 else
			 {
				 objRedir.addFlashAttribute("mensaje","ocurrio un roche");
				 return "redirect:/ciudad/irRegistrar";
			 }
		 }
	}	
		
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, 
			RedirectAttributes objRedir) throws ParseException {
		 
		Optional<Ciudad> objCiudad = pService.listarId(id);
		if (objCiudad == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error al cargar");
			return "redirect:/ciudad/listar";
		}
		else {
			model.addAttribute("ciudad", objCiudad);
			model.addAttribute("listaPaises", cService.listar());
			if (objCiudad.isPresent())
				objCiudad.ifPresent(o -> model.addAttribute("ciudad", o));	
			return "ciudad";
		}				
	}	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaCiudades", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un gran rochetov");
			model.put("listaCiudades", pService.listar());
		}
		return "listCiudades";
	}
		
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Ciudad ciudad) {
		pService.listarId(ciudad.getIdCiudad());
		return "listCiudades";
	}
	
	@GetMapping("/buscar/{nameCiudad}")
	public String buscarName(@PathVariable(value = "nameCiudad") String nameCiudad,Map<String, Object> model, @ModelAttribute Pais pais) {
		model.put("listaCiudades", pService.buscarCiudad(nameCiudad));
		return "listCiudades";
	}
}
