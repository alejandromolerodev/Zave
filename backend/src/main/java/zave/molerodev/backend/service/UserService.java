package zave.molerodev.backend.service;

import java.util.List;

import zave.molerodev.backend.entities.User;

public interface UserService {

    User findById(Long id);
    User save(User user);
    List<User> findAll();
    void deleteById(Long id);
    User findByEmail(String email);
    User findByUsername(String username);
    User update(User user);
    Boolean verificarContraseña(String password, String hashedPassword);
    String encriptarContraseña(String password);
}
