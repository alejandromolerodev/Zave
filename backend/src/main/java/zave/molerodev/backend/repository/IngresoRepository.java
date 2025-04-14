package zave.molerodev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zave.molerodev.backend.entities.Ingreso;


@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {


}
