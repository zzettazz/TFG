package proyecto.tfg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.tfg.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByCodigoActivacion(String codigoActivacion);
    Usuario findByNomUsuario(String nomUsuario);
    Usuario findByEmail(String email);
    Usuario[] findAllByNomUsuario(String nomUsuario);
}
