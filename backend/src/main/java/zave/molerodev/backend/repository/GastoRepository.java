package zave.molerodev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zave.molerodev.backend.entities.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
   
}
