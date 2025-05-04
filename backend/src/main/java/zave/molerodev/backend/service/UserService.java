package zave.molerodev.backend.service;

import java.util.List;

import zave.molerodev.backend.entities.Usuario;

public interface UserService {

    Usuario findById(Long id);
    Usuario save(Usuario user);
    List<Usuario> findAll();
    void deleteById(Long id);
    Usuario findByEmail(String email);
    Usuario findByUsername(String username);
    Usuario update(Usuario user);
}
