package pe.edu.upc.spring.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.spring.model.Usuario;

@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Integer>{
	@Query("from Usuario c where c.email like %:email%")
	List<Usuario> buscarUsuario(@Param("email")String email);
}
