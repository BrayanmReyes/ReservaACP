package pe.edu.upc.spring.serviceimpl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Factura;
import pe.edu.upc.spring.repository.IFacturaDao;
import pe.edu.upc.spring.service.IFacturaService;

@Service
public class FacturaServiceImpl  implements IFacturaService{
	@Autowired
	private IFacturaDao dFactura;

	@Override
	@Transactional
	public boolean insertar(Factura oFactura) {
		Factura objFactura = dFactura.save(oFactura);
		if (objFactura == null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Factura oFactura) {
		boolean flag = false;
		try {
			dFactura.save(oFactura);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idFactura) {
		dFactura.deleteById(idFactura);
	}

	@Override
	public Optional<Factura> buscarId(int idFactura) {
		return dFactura.findById(idFactura);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Factura>  listarId(int idFactura) {
		return dFactura.findById(idFactura);
	}
		
	@Override
	@Transactional(readOnly=true)
	public List<Factura> listar() {
		return dFactura.findAll();		
	}
	
	@Override
	public List<Factura> buscarFactura(String numFactura) {
		return dFactura.buscarFactura(numFactura);
	}

}
