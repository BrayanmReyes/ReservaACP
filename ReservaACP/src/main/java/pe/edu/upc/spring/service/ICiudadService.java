package pe.edu.upc.spring.service;
import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Ciudad;
public interface ICiudadService {
	public boolean insertar(Ciudad ciudad);
	public boolean modificar(Ciudad ciudad);
	public void eliminar(int idCiudad);
	public Optional<Ciudad> buscarId(int idCiudad);
	public Optional<Ciudad> listarId(int idCiudad);
	List<Ciudad> listar();
	List<Ciudad> buscarCiudad(String nameCiudad);
}
