package zave.molerodev.backend.entities;

import java.math.BigDecimal;
import java.time.YearMonth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ResumenMensual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private YearMonth mes; // Formato: YYYY-MM

    private BigDecimal totalIngresos;

    private BigDecimal totalGastos;

    private BigDecimal totalAhorro;
}
