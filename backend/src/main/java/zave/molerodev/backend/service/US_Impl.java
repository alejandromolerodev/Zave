package zave.molerodev.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zave.molerodev.backend.entities.Usuario;
import zave.molerodev.backend.repository.UserRepository;

@Service
public class US_Impl implements UserService{

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public Usuario findById(Long id) {
       
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario findByEmail(String email) {

        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Usuario findByUsername(String username) {

        return userRepository.findByUsername(username).orElse(null);
    }

   

    @Override
    public Usuario update(Usuario user) {
        Usuario existingUser = userRepository.findById(user.getId()).orElse(null);
    
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setRol(user.getRol());
            existingUser.setPassword(user.getPassword());
    
           
    
            return userRepository.save(existingUser);
        }
    
        return null;
    }
    

    @Override
    public Usuario save(Usuario user) {
        return userRepository.save(user);
    }

    

    @Override
    public List<Usuario> findAll() {
        return userRepository.findAll();
    }

   
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }

}
