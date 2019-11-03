package pe.edu.upc.spring.serviceimpl;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Reserva;
import pe.edu.upc.spring.repository.IReservaDao;
import pe.edu.upc.spring.service.IReservaService;

@Service
public class ReservaServiceImpl  implements IReservaService{
	@Autowired
	private IReservaDao dReserva;

	@Override
	@Transactional
	public boolean insertar(Reserva oReserva) {
		Reserva objReserva = dReserva.save(oReserva);
		if (objReserva == null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Reserva oReserva) {
		boolean flag = false;
		try {
			dReserva.save(oReserva);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idReserva) {
		dReserva.deleteById(idReserva);
	}

	@Override
	public Optional<Reserva> buscarId(int idReserva) {
		return dReserva.findById(idReserva);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Reserva>  listarId(int idReserva) {
		return dReserva.findById(idReserva);
	}
		
	@Override
	@Transactional(readOnly=true)
	public List<Reserva> listar() {
		return dReserva.findAll();		
	}
	
	@Override
	public List<Reserva> buscarReserva(Date dayReserva) {
		return dReserva.buscarReserva(dayReserva);
	}

}