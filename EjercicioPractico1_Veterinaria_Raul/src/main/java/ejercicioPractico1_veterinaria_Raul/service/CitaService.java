package ejercicioPractico1_veterinaria_Raul.service;

import ejercicioPractico1_veterinaria_Raul.domain.Cita;
import ejercicioPractico1_veterinaria_Raul.repository.CitaRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CitaService {

    private final CitaRepository citaRepositorio;

    public CitaService(CitaRepository citaRepositorio) {
        this.citaRepositorio = citaRepositorio;
    }

    @Transactional(readOnly = true)
    public List<Cita> obtenerCitas() {
        return citaRepositorio.findAll();
    }
}
