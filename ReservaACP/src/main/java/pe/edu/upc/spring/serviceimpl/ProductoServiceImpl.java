package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Producto;
import pe.edu.upc.spring.repository.IProductoDao;
import pe.edu.upc.spring.service.IProductoService;

@Service
public class ProductoServiceImpl  implements IProductoService{
	@Autowired
	private IProductoDao dProducto;

	@Override
	@Transactional
	public boolean insertar(Producto oProducto) {
		Producto objProducto = dProducto.save(oProducto);
		if (objProducto == null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Producto oProducto) {
		boolean flag = false;
		try {
			dProducto.save(oProducto);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idProducto) {
		dProducto.deleteById(idProducto);
	}

	@Override
	public Optional<Producto> buscarId(int idProducto) {
		return dProducto.findById(idProducto);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Producto>  listarId(int idProducto) {
		return dProducto.findById(idProducto);
	}
		
	@Override
	@Transactional(readOnly=true)
	public List<Producto> listar() {
		return dProducto.findAll();		
	}
	
	@Override
	public List<Producto> buscarProducto(String nameProducto) {
		return dProducto.buscarProducto(nameProducto);
	}
	

}
