package zave.molerodev.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zave.molerodev.backend.entities.User;
import zave.molerodev.backend.service.UserService;

@RestController
@RequestMapping("/api/zave/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }


    @PutMapping("/id/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userService.findById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        // Aseguramos que el ID en el user coincida con el path variable
        user.setId_usuario(id);

        User updatedUser = userService.update(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/email/{email}")
    public ResponseEntity<User> updateUserByEmail(@PathVariable String email, @RequestBody User user) {
        User existingUser = userService.findByEmail(email);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        user.setId_usuario(existingUser.getId_usuario()); // Para que el update funcione bien
        
        User updatedUser = userService.update(user);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        User existingUser = userService.findById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
