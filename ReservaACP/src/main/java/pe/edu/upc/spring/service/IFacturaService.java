package pe.edu.upc.spring.service;
import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Factura;
public interface IFacturaService {
	public boolean insertar(Factura factura);
	public boolean modificar(Factura factura);
	public void eliminar(int idFactura);
	public Optional<Factura> buscarId(int idFactura);
	public Optional<Factura> listarId(int idFactura);
	List<Factura> listar();
	List<Factura> buscarFactura(String numFactura);
}
