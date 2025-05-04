package zave.molerodev.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zave.molerodev.backend.entities.Cuenta;
import zave.molerodev.backend.entities.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
   
        List<Gasto> findByCuenta(Cuenta cuenta);

}
