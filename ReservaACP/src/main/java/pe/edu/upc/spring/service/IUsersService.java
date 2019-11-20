package pe.edu.upc.spring.service;
import java.util.Optional;

import pe.edu.upc.spring.model.Users;
public interface IUsersService {
	public boolean insertar(Users User);
	public Optional<Users> buscarId(int idUsuario);
	public Optional<Users> listarId(int idUsuario);
}
