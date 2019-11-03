package pe.edu.upc.spring.serviceimpl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Ciudad;
import pe.edu.upc.spring.repository.ICiudadDao;
import pe.edu.upc.spring.service.ICiudadService;

@Service
public class CiudadServiceImpl  implements ICiudadService{
	@Autowired
	private ICiudadDao dCiudad;

	@Override
	@Transactional
	public boolean insertar(Ciudad oCiudad) {
		Ciudad objCiudad = dCiudad.save(oCiudad);
		if (objCiudad == null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Ciudad oCiudad) {
		boolean flag = false;
		try {
			dCiudad.save(oCiudad);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idCiudad) {
		dCiudad.deleteById(idCiudad);
	}

	@Override
	public Optional<Ciudad> buscarId(int idCiudad) {
		return dCiudad.findById(idCiudad);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Ciudad> listarId(int idCiudad) {
		return dCiudad.findById(idCiudad);
	}
		
	@Override
	@Transactional(readOnly=true)
	public List<Ciudad> listar() {
		return dCiudad.findAll();		
	}
	
	@Override
	public List<Ciudad> buscarCiudad(String nameCiudad) {
		return dCiudad.buscarCiudad(nameCiudad);
	}

}
