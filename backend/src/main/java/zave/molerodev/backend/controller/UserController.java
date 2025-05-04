package zave.molerodev.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import zave.molerodev.backend.entities.Usuario;
import zave.molerodev.backend.model.AuthRequest;
import zave.molerodev.backend.service.UserService;

@RestController
@RequestMapping("/api/zave/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    // Registro de un usuario sin autenticación
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        if (userService.findByEmail(request.getEmail()) != null) {
            return ResponseEntity.status(400).body("Ya existe un usuario con ese correo");
        }
    
        Usuario newUser = new Usuario();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        newUser.setRol("USER");
        newUser.setUsername(request.getNombre());
    
        userService.save(newUser);
        return ResponseEntity.ok(Map.of("userId", newUser.getId())); // 👈 Esto es clave
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Usuario user = userService.findByEmail(request.getEmail());
        if (user == null) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    
        return ResponseEntity.ok(Map.of("userId", user.getId())); // 👈 También aquí
    }
    




    // Obtención de todos los usuarios
    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    // Obtención de un usuario por ID
    @GetMapping("/id/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    // Obtención de un usuario por email
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @GetMapping("/username/{id}")
    public ResponseEntity<String> getUsernameById(@PathVariable Long id) {
        Usuario user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
        return ResponseEntity.ok(user.getUsername());
    }

    // Creación de un usuario
    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario user) {
        return ResponseEntity.ok(userService.save(user));
    }

    // Actualización de un usuario por ID
    @PutMapping("/id/{id}")
    public ResponseEntity<Usuario> updateUserById(@PathVariable Long id, @RequestBody Usuario user) {
        Usuario existingUser = userService.findById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        user.setId(id);
        return ResponseEntity.ok(userService.update(user));
    }

    // Actualización de un usuario por email
    @PutMapping("/email/{email}")
    public ResponseEntity<Usuario> updateUserByEmail(@PathVariable String email, @RequestBody Usuario user) {
        Usuario existingUser = userService.findByEmail(email);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        user.setId(existingUser.getId());
        return ResponseEntity.ok(userService.update(user));
    }

    // Eliminación de un usuario por ID
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        Usuario existingUser = userService.findById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
