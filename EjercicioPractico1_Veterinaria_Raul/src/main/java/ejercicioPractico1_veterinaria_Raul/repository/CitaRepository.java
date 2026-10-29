package ejercicioPractico1_veterinaria_Raul.repository;

import ejercicioPractico1_veterinaria_Raul.domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository
        extends JpaRepository<Cita, Integer> {

}
