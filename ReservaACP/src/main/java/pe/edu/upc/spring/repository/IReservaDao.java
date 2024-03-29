package pe.edu.upc.spring.repository;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.spring.model.Reserva;

@Repository
public interface IReservaDao extends JpaRepository<Reserva, Integer>{
	@Query("from Reserva c where c.dayReserva like %:dayReserva%")
	List<Reserva> buscarReserva(@Param("dayReserva")Date dayReserva);
}
