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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pe.edu.upc.spring.model.Pedido;
import pe.edu.upc.spring.model.Producto;
import pe.edu.upc.spring.model.Reserva;
import pe.edu.upc.spring.service.IPedidoService;
import pe.edu.upc.spring.service.IProductoService;
import pe.edu.upc.spring.service.IReservaService;



@Controller
@RequestMapping("/pedido")
public class PedidoController {
	@Autowired
	private IPedidoService pService;
	@Autowired
	private IProductoService cService;
	@Autowired
	private IReservaService rService;
	
	private double aux;
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPedidos", pService.listar());
		model.put("listaProductos", cService.listar());
		return "listPedidos";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("pedido", new Pedido());
		model.addAttribute("listaProductos", cService.listar());
		return "pedido";
	}
	
	@GetMapping("/usuarioIrRegistrar")
	public String usuarioIrRegistrar(@RequestParam("idReserva") int idReserva,Model model,@ModelAttribute @Valid Pedido objPedido) {

		model.addAttribute("pedidoUsuario", new Pedido());
		model.addAttribute("pedidoUsuario", objPedido);
		//int c = objPedido.getReserva().getIdReserva();
		model.addAttribute("idReserva", objPedido.getReserva().getIdReserva());
		model.addAttribute("listaProductos", cService.listar());
		return "pedidoUsuario";
	}
	
	@GetMapping("/form/{idReserva}")
	public String newInvoice(@PathVariable(value = "idReserva") int idReserva, Model model) {
		try {
			Optional<Reserva> reserva = rService.buscarId(idReserva);
			if (!reserva.isPresent()) {
				model.addAttribute("info", "Reserva no existe");
				return "redirect:/invoices/list";
			} else {
				Pedido pedido = new Pedido();
				pedido.setReserva(reserva.get());
				model.addAttribute("pedidoUsuario", pedido);
				model.addAttribute("listaProductos", cService.listar());
				//model.addAttribute("title", "Factura");
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "pedidoUsuario";
	}

	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Pedido objPedido,  
			BindingResult binRes, Model model) throws ParseException {
		 if (binRes.hasErrors()) {
			 model.addAttribute("listaProductos", cService.listar());		 
			 return "pedido";
		 }
		 else {
			 boolean flag = pService.insertar(objPedido);
			 
			 Optional<Producto> objProducto = cService.listarId(objPedido.getProducto().getIdProducto());
			 objPedido.setProducto(objProducto.get());
			 aux=objPedido.getProducto().getQuantityReserva()+objPedido.getQuantityPeso();
			 objProducto.get().setQuantityReserva(aux);
			 
			 if (objProducto.isPresent()) {
					objProducto.ifPresent(o -> model.addAttribute("producto", o));
					return "producto";
			 }
			 
			 if (flag) {	
				 pService.updateReserva(aux, objPedido.getIdPedido());
				 return "redirect:/pedido/listar";
			 }
			 
			 else
			 {
				 model.addAttribute("mensaje","ocurrio un roche");
				 return "redirect:/pedido/irRegistrar";
			 }
			 
		 }
	}
	@RequestMapping("/registrarxUsuario")
	public String registrarUsuario(@ModelAttribute @Valid Pedido objPedido,  
			BindingResult binRes, Model model) throws ParseException {
		 if (binRes.hasErrors()) {
			 model.addAttribute("listaProductos", cService.listar());		 
			 return "pedidoUsuario";
		 }
		 else {
			 boolean flag = pService.insertar(objPedido);
			 
			 Optional<Producto> objProducto = cService.listarId(objPedido.getProducto().getIdProducto());
			 objPedido.setProducto(objProducto.get());
			 aux=objPedido.getProducto().getQuantityReserva()+objPedido.getQuantityPeso();
			 objProducto.get().setQuantityReserva(aux);
			 
			 if (objProducto.isPresent()) {
					objProducto.ifPresent(o -> model.addAttribute("producto", o));
					//pService.updateReserva(aux, objPedido.getIdPedido());
					return "producto";
					//return "redirect:/producto/listar";
			 }
			 
			 if (flag) {	
				 pService.updateReserva(aux, objPedido.getIdPedido());
				 return "redirect:/pedido/listar";
			 }
			 
			 else
			 {
				 model.addAttribute("mensaje","ocurrio un roche");
				 return "redirect:/pedido/form/{idReserva}";
			 }
			 
		 }
	}

	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Pedido objPedido, 
			BindingResult binRes, Model model, RedirectAttributes objRedir) throws ParseException {
		 if (binRes.hasErrors()) {
			 return "redirect://pedido/listar";
		 }
		 else 
		 {
			 boolean flag = pService.modificar(objPedido);
			 
			 if (flag) 
			 {
				 objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				 return "redirect:/pedido/listar";
			 }			 
			 else
			 {
				 objRedir.addFlashAttribute("mensaje","ocurrio un roche");
				 return "redirect:/pedido/irRegistrar";
			 }
		 }
	}	
		
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, 
			RedirectAttributes objRedir) throws ParseException {
		 
		Optional<Pedido> objPedido = pService.listarId(id);
		if (objPedido == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error al cargar");
			return "redirect:/pedido/listar";
		}
		else {
			model.addAttribute("pedido", objPedido);
			model.addAttribute("listaProductos",cService.listar());
			if (objPedido.isPresent())
				objPedido.ifPresent(o->model.addAttribute("pedido",o));
			return "pedido";
		}				
	}	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaPedidos", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un gran rochetov");
			model.put("listaPedidos", pService.listar());
		}
		return "listPedidos";
	}
		
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Pedido pedido) {
		//pService.listarId(pedido.getIdPedido());
		pService.listarId(pedido.getReserva().getIdReserva());
		return "listPedidos";
	}
	
	@GetMapping("/buscarid")
	public String buscarName(@RequestParam("idReserva") int idReserva,Map<String, Object> model) {
		model.put("listaPedidos", pService.buscarPedidoxIdreserva(idReserva));
		return "listPedidos";
	}
	
	@PostMapping("/buscarobj")
	public String buscarObjeto(@RequestParam("reserva") Reserva reserva,Map<String, Object> model) {
		model.put("listaPedidos", pService.buscarPedido(reserva));
		return "listPedidos";
	}
}
