package zave.molerodev.backend.entities;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    private String nombre;

    private String email;

    private String contraseña;

    private String rol;

    @ManyToOne
    @JoinColumn(name = "id_plan_activo", nullable = true)
    private PlanAhorro planAhorroActivo;
}
