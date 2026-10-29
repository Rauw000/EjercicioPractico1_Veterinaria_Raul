package ejercicioPractico1_veterinaria_Raul.repository;

import ejercicioPractico1_veterinaria_Raul.domain.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoServicioRepository
        extends JpaRepository<TipoServicio, Integer> {

}
