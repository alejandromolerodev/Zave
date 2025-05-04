package zave.molerodev.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zave.molerodev.backend.entities.Cuenta;
import zave.molerodev.backend.entities.Ingreso;


@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {

        List<Ingreso> findByCuenta(Cuenta cuenta);


}
