package pe.edu.upc.spring.serviceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Users;
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
	
}
