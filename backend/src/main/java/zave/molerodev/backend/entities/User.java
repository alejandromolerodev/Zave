package zave.molerodev.backend.entities;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    private String nombre;

    private String email;

    private String password;

    private String rol;

    @ManyToOne
    @JoinColumn(name = "id_plan_activo", nullable = true)
    private PlanAhorro planAhorroActivo;

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public PlanAhorro getPlanAhorroActivo() {
        return planAhorroActivo;
    }

    public void setPlanAhorroActivo(PlanAhorro planAhorroActivo) {
        this.planAhorroActivo = planAhorroActivo;
    }


    

}
