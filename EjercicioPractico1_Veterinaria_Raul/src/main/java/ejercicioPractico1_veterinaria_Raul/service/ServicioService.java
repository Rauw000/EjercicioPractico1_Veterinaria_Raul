package ejercicioPractico1_veterinaria_Raul.service;

import ejercicioPractico1_veterinaria_Raul.domain.Servicio;
import ejercicioPractico1_veterinaria_Raul.repository.ServicioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepositorio;

    public ServicioService(ServicioRepository servicioRepositorio) {
        this.servicioRepositorio = servicioRepositorio;
    }

    @Transactional(readOnly = true)
    public List<Servicio> obtenerServicios() {
        return servicioRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Servicio> buscarServicio(Integer id) {
        return servicioRepositorio.findById(id);
    }

    @Transactional
    public void guardarServicio(Servicio servicio) {
        servicioRepositorio.save(servicio);
    }

    @Transactional
    public void eliminarServicio(Integer id) {
        servicioRepositorio.deleteById(id);
    }
}
