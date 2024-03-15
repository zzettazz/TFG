package proyecto.tfg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.tfg.domain.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje,Long> {
    
}
