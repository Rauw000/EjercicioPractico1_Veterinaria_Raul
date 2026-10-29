package ejercicioPractico1_veterinaria_Raul.service;

import ejercicioPractico1_veterinaria_Raul.domain.TipoServicio;
import ejercicioPractico1_veterinaria_Raul.repository.TipoServicioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoServicioService {

    private final TipoServicioRepository tipoRepositorio;

    public TipoServicioService(TipoServicioRepository tipoRepositorio) {
        this.tipoRepositorio = tipoRepositorio;
    }

    @Transactional(readOnly = true)
    public List<TipoServicio> obtenerTipos() {

        List<TipoServicio> tipos = tipoRepositorio.findAll();

        for (TipoServicio tipo : tipos) {
            tipo.getServicios().size();
        }

        return tipos;
    }

    @Transactional(readOnly = true)
    public Optional<TipoServicio> buscarTipo(Integer id) {
        return tipoRepositorio.findById(id);
    }

    @Transactional
    public void guardarTipo(TipoServicio tipo) {
        tipoRepositorio.save(tipo);
    }

    @Transactional
    public void eliminarTipo(Integer id) {
        tipoRepositorio.deleteById(id);
    }
}
