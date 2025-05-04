package zave.molerodev.backend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zave.molerodev.backend.entities.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByUsername(String username);  // Cambié "nombre" por "username"

    void deleteByEmail(String email);
}
