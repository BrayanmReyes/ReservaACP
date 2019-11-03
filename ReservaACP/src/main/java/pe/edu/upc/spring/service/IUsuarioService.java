package pe.edu.upc.spring.service;
import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Usuario;
public interface IUsuarioService {
	public boolean insertar(Usuario Usuario);
	public boolean modificar(Usuario Usuario);
	public void eliminar(int idUsuario);
	public Optional<Usuario> buscarId(int idUsuario);
	public Optional<Usuario> listarId(int idUsuario);
	List<Usuario> listar();
	List<Usuario> buscarUsuario(String email);
}
