package pe.edu.upc.spring.serviceimpl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.repository.IUsuarioDao;
import pe.edu.upc.spring.service.IUsuarioService;

@Service
public class UsuarioServiceImpl  implements IUsuarioService{
	@Autowired
	private IUsuarioDao dUsuario;

	@Override
	@Transactional
	public boolean insertar(Usuario oUsuario) {
		Usuario objUsuario = dUsuario.save(oUsuario);
		if (objUsuario == null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Usuario oUsuario) {
		boolean flag = false;
		try {
			dUsuario.save(oUsuario);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idUsuario) {
		dUsuario.deleteById(idUsuario);
	}

	@Override
	public Optional<Usuario> buscarId(int idUsuario) {
		return dUsuario.findById(idUsuario);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Usuario>  listarId(int idUsuario) {
		return dUsuario.findById(idUsuario);
	}
		
	@Override
	@Transactional(readOnly=true)
	public List<Usuario> listar() {
		return dUsuario.findAll();		
	}
	
	@Override
	public List<Usuario> buscarUsuario(String email) {
		return dUsuario.buscarUsuario(email);
	}

}