package pe.edu.upc.spring.repository;
import java.util.List;

//import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import pe.edu.upc.spring.model.Pedido;
import pe.edu.upc.spring.model.Reserva;

@Repository
public interface IPedidoDao extends JpaRepository<Pedido, Integer>{
	@Query("from Pedido c where c.reserva like %:reserva%")
	List<Pedido> buscarPedido(@Param("reserva")Reserva reserva);
	
	
	@Query("update Producto u set u.quantityReserva = :quantityReserva where u.idProducto = :idProducto")
	@Transactional(readOnly = false, propagation = Propagation.NESTED,rollbackFor=Exception.class )
	@Modifying
	public boolean updateReserva(@Param("quantityReserva") double quantityReserva, @Param("idProducto") Integer idProducto);
	//public boolean updateReserva(@Param("quantityReserva") double quantityReserva, @Param("idProducto") int idProducto);
	
	@Query("from Pedido c where c.reserva.idReserva = :idReserva")
	List<Pedido> buscarPedidoxIdreserva(@Param("idReserva")int idReserva);
	
}
