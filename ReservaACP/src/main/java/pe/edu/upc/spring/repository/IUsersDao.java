package pe.edu.upc.spring.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.spring.model.Users;

@Repository
public interface IUsersDao extends JpaRepository<Users, Integer> {

}
