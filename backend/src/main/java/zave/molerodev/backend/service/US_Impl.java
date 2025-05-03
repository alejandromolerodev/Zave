package zave.molerodev.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import zave.molerodev.backend.entities.User;
import zave.molerodev.backend.repository.UserRepository;

@Service
public class US_Impl implements UserService{

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User findById(Long id) {
       
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username).orElse(null);
    }

   

    @Override
    public User update(User user) {
        User existingUser = userRepository.findById(user.getId_usuario()).orElse(null);
    
        if (existingUser != null) {
            existingUser.setNombre(user.getNombre());
            existingUser.setEmail(user.getEmail());
            existingUser.setRol(user.getRol());
            existingUser.setPlanAhorroActivo(user.getPlanAhorroActivo());
    
            if (user.getPassword() != null && !user.getPassword().isBlank() &&
                !passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
    
            return userRepository.save(existingUser);
        }
    
        return null;
    }
    

    @Override
    public User save(User user) {
        if (user.getPassword() != null) {
            // Encriptar la contraseña solo si no está ya encriptada
            if (!passwordEncoder.matches(user.getPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }
        return userRepository.save(user);
    }

    @Override
    public Boolean verificarContraseña(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }

    @Override
    public String encriptarContraseña(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

   
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }

}
