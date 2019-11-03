package pe.edu.upc.spring.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.spring.model.Ciudad;

@Repository
public interface ICiudadDao extends JpaRepository<Ciudad, Integer>{
	@Query("from Ciudad c where c.nameCiudad like %:nameCiudad%")
	List<Ciudad> buscarCiudad(@Param("nameCiudad")String nameCiudad);
}
