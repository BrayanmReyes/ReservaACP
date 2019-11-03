package pe.edu.upc.spring.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.spring.model.Pais;

@Repository
public interface IPaisDao extends JpaRepository<Pais, Integer>{
	@Query("from Pais c where c.namePais like %:namePais%")
	List<Pais> buscarPais(@Param("namePais")String namePais);
}