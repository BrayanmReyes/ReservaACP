package pe.edu.upc.spring.service;
import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Pais;
public interface IPaisService {
	public boolean insertar(Pais pais);
	public boolean modificar(Pais pais);
	public void eliminar(int idPais);
	public Optional<Pais> buscarId(int idPais);
	public Optional<Pais> listarId(int idPais);
	List<Pais> listar();
	List<Pais> buscarPais(String namePais);
}
