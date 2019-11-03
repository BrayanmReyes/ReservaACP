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

import pe.edu.upc.spring.model.Factura;
import pe.edu.upc.spring.service.IFacturaService;

@Controller
@RequestMapping("/factura")
public class FacturaController {
	@Autowired
	private IFacturaService pService;
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaFacturas", pService.listar());
		return "listFacturas";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("factura", new Factura());
		return "factura";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Factura objFactura, 
			BindingResult binRes, Model model) throws ParseException {
		 if (binRes.hasErrors()) {
			 return "factura";
		 }
		 else {
			 boolean flag = pService.insertar(objFactura);
			 if (flag)
				 return "redirect:/factura/listar";
			 else
			 {
				 model.addAttribute("mensaje","ocurrio un roche");
				 return "redirect:/factura/irRegistrar";
			 }
		 }
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Factura objFactura, 
			BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException {
		 if (binRes.hasErrors()) {
			 return "redirect://factura/listar";
		 }
		 else 
		 {
			 boolean flag = pService.modificar(objFactura);
			 if (flag) 
			 {
				 objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				 return "redirect:/factura/listar";
			 }			 
			 else
			 {
				 objRedir.addFlashAttribute("mensaje","ocurrio un roche");
				 return "redirect:/factura/irRegistrar";
			 }
		 }
	}	
		
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, 
			RedirectAttributes objRedir) throws ParseException {
		 
		Optional<Factura> objFactura = pService.listarId(id);
		if (objFactura == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error al cargar");
			return "redirect:/factura/listar";
		}
		else {
			model.addAttribute("factura", objFactura);
			return "factura";
		}				
	}	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaFacturas", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un gran rochetov");
			model.put("listaFacturas", pService.listar());
		}
		return "listFacturas";
	}
		
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Factura factura) {
		pService.listarId(factura.getIdFactura());
		return "listFacturas";
	}
	
}
