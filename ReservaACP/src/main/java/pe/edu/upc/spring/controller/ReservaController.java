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

import pe.edu.upc.spring.model.Reserva;
import pe.edu.upc.spring.service.ICiudadService;
import pe.edu.upc.spring.service.IReservaService;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/reserva")
public class ReservaController {
	@Autowired
	private IReservaService pService;
	
	@Autowired
	private ICiudadService cService;
	
	@Autowired
	private IUsuarioService uService;
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaReservas", pService.listar());
		model.put("listaCiudades", cService.listar());
		model.put("listaUsuarios",uService.listar());
		
		return "listReservas";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("reserva", new Reserva());
		model.addAttribute("listaCiudades", cService.listar());
		model.addAttribute("listaUsuarios", uService.listar());
		
		return "reserva";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Reserva objReserva, 
			BindingResult binRes, Model model) throws ParseException {
		 if (binRes.hasErrors()) {
			 model.addAttribute("listaCiudades", cService.listar());
			 model.addAttribute("listaUsuarios", uService.listar());
			 return "reserva";
		 }
		 else {
			 boolean flag = pService.insertar(objReserva);
			 if (flag)
				 return "redirect:/reserva/listar";
			 else
			 {
				 model.addAttribute("mensaje","ocurrio un roche");
				 return "redirect:/reserva/irRegistrar";
			 }
		 }
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Reserva objReserva, 
			BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException {
		 if (binRes.hasErrors()) {
			 return "redirect://reserva/listar";
		 }
		 else 
		 {
			 boolean flag = pService.modificar(objReserva);
			 if (flag) 
			 {
				 objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				 return "redirect:/reserva/listar";
			 }			 
			 else
			 {
				 objRedir.addFlashAttribute("mensaje","ocurrio un roche");
				 return "redirect:/reserva/irRegistrar";
			 }
		 }
	}	
		
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, 
			RedirectAttributes objRedir) throws ParseException {
		 
		Optional<Reserva> objReserva = pService.listarId(id);
		if (objReserva == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error al cargar");
			return "redirect:/pedido/listar";
		}
		else {
			model.addAttribute("reserva", objReserva);
			model.addAttribute("listaCiudades", cService.listar());
			model.addAttribute("listaUsuarios", uService.listar());
			if (objReserva.isPresent())
				objReserva.ifPresent(o -> model.addAttribute("reserva", o));	
			return "reserva";
		}				
	}	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaReservas", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un gran roche");
			model.put("listaReservas", pService.listar());
		}
		return "listReservas";
	}
		
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Reserva reserva) {
		pService.listarId(reserva.getIdReserva());
		return "listReservas";
	}
	
}
