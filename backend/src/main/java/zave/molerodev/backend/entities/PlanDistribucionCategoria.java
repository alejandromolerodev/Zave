package zave.molerodev.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PlanDistribucionCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_plan")
    private PlanAhorro plan;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    private double porcentaje;
}
