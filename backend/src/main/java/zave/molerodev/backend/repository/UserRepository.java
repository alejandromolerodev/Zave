package zave.molerodev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zave.molerodev.backend.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
