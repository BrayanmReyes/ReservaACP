package pe.edu.upc.spring.serviceimpl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Users;
import pe.edu.upc.spring.model.Usuario;
import pe.edu.upc.spring.repository.IUsersDao;
import pe.edu.upc.spring.service.IUsersService;
@Service
public class UsersServiceImpl implements IUsersService{
	@Autowired
	private IUsersDao dUser;

	@Override
	@Transactional
	public boolean insertar(Users oUsuario) {
		Users objUsuario = dUser.save(oUsuario);
		if (objUsuario == null)
			return false;
		else
			return true;
	}
	@Override
	public Optional<Users> buscarId(int idUsuario) {
		return dUser.findById(idUsuario);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Users>  listarId(int idUsuario) {
		return dUser.findById(idUsuario);
	}
}
