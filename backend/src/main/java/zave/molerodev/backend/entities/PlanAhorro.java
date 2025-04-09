package zave.molerodev.backend.entities;

import jakarta.persistence.*;

@Entity
public class PlanAhorro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_plan;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = true)
    private User usuario;

    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private double porcentajeAhorro;

    private boolean esPredeterminado;
}
