package pe.edu.upc.spring.serviceimpl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Pais;
import pe.edu.upc.spring.repository.IPaisDao;
import pe.edu.upc.spring.service.IPaisService;

@Service
public class PaisServiceImpl  implements IPaisService{
	@Autowired
	private IPaisDao dPais;

	@Override
	@Transactional
	public boolean insertar(Pais oPais) {
		Pais objPais = dPais.save(oPais);
		if (objPais == null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Pais oPais) {
		boolean flag = false;
		try {
			dPais.save(oPais);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idPais) {
		dPais.deleteById(idPais);
	}

	@Override
	public Optional<Pais> buscarId(int idPais) {
		return dPais.findById(idPais);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Pais>  listarId(int idPais) {
		return dPais.findById(idPais);
	}
		
	@Override
	@Transactional(readOnly=true)
	public List<Pais> listar() {
		return dPais.findAll();		
	}
	
	@Override
	public List<Pais> buscarPais(String namePais) {
		return dPais.buscarPais(namePais);
	}

}