package pe.edu.upc.spring.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.spring.model.Producto;

@Repository
public interface IProductoDao extends JpaRepository<Producto, Integer>{
	@Query("from Producto c where c.nameProducto like %:nameProducto%")
	List<Producto> buscarProducto(@Param("nameProducto")String nameProducto);
	
}
