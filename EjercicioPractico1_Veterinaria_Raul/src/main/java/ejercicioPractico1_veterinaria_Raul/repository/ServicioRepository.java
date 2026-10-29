package ejercicioPractico1_veterinaria_Raul.repository;

import ejercicioPractico1_veterinaria_Raul.domain.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository
        extends JpaRepository<Servicio, Integer> {

}