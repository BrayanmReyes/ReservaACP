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

import pe.edu.upc.spring.model.Pais;
import pe.edu.upc.spring.model.Producto;
import pe.edu.upc.spring.service.IProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	private IProductoService pService;
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaProductos", pService.listar());
		return "listProductos";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("producto", new Producto());
		return "producto";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Producto objProducto, 
			BindingResult binRes, Model model) throws ParseException {
		 if (binRes.hasErrors()) {
			 return "producto";
		 }
		 else {
			 boolean flag = pService.insertar(objProducto);
			 if (flag)
				 return "redirect:/producto/listar";
			 else
			 {
				 model.addAttribute("mensaje","ocurrio un roche");
				 return "redirect:/producto/irRegistrar";
			 }
		 }
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Producto objProducto, 
			BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException {
		 if (binRes.hasErrors()) {
			 return "redirect://producto/listar";
		 }
		 else 
		 {
			 boolean flag = pService.modificar(objProducto);
			 if (flag) 
			 {
				 objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				 return "redirect:/producto/listar";
			 }			 
			 else
			 {
				 objRedir.addFlashAttribute("mensaje","ocurrio un roche");
				 return "redirect:/producto/irRegistrar";
			 }
		 }
	}	
		
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, 
			RedirectAttributes objRedir) throws ParseException {
		 
		Optional<Producto> objProducto = pService.listarId(id);
		if (objProducto == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error al cargar");
			return "redirect:/producto/listar";
		}
		else {
			model.addAttribute("producto", objProducto);
			if (objProducto.isPresent())
				objProducto.ifPresent(o->model.addAttribute("producto",o));
			return "producto";
		}				
	}	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaProductos", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un gran rocheto");
			model.put("listaProductos", pService.listar());
		}
		return "listProductos";
	}
		
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Producto producto) {
		pService.listarId(producto.getIdProducto());
		return "listProductos";
	}
	
	@GetMapping("/buscar/{nameProducto}")
	public String buscarName(@PathVariable(value = "nameProducto") String nameProducto,Map<String, Object> model, @ModelAttribute Pais pais) {
		model.put("listaproductos", pService.buscarProducto(nameProducto));
		return "listProductos";
	}
	
}
