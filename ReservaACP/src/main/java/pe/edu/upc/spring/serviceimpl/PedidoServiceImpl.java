package pe.edu.upc.spring.serviceimpl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Pedido;
import pe.edu.upc.spring.model.Reserva;
import pe.edu.upc.spring.repository.IPedidoDao;
import pe.edu.upc.spring.service.IPedidoService;

@Service
public class PedidoServiceImpl  implements IPedidoService{
	@Autowired
	private IPedidoDao dPedido;

	@Override
	@Transactional
	public boolean insertar(Pedido oPedido) {
		Pedido objPedido = dPedido.save(oPedido);
		if (objPedido == null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Pedido oPedido) {
		boolean flag = false;
		try {
			dPedido.save(oPedido);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idPedido) {
		dPedido.deleteById(idPedido);
	}

	@Override
	public Optional<Pedido> buscarId(int idPedido) {
		return dPedido.findById(idPedido);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Pedido>  listarId(int idPedido) {
		return dPedido.findById(idPedido);
	}
		
	@Override
	@Transactional(readOnly=true)
	public List<Pedido> listar() {
		return dPedido.findAll();		
	}
	
	@Override
	public List<Pedido> buscarPedido(Reserva reserva) {
		return dPedido.buscarPedido(reserva);
	}
	
	@Override
	public int updateReserva (double quantityReserva,int idProducto) {
		return dPedido.updateReserva(quantityReserva, idProducto);
	}
	
	@Override
	public List<Pedido> buscarPedidoxIdreserva(int idReserva) {
		return dPedido.buscarPedidoxIdreserva(idReserva);
	}
	
}