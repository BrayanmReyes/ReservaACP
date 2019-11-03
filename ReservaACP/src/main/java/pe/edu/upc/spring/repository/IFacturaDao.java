package pe.edu.upc.spring.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.spring.model.Factura;

@Repository
public interface IFacturaDao extends JpaRepository<Factura, Integer>{
	@Query("from Factura c where c.numFactura like %:numFactura%")
	List<Factura> buscarFactura(@Param("numFactura")String numFactura);
}

