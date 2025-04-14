package zave.molerodev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zave.molerodev.backend.entities.ResumenMensual;

@Repository
public interface ResumenMRepository extends JpaRepository<ResumenMensual, Long> {

}
