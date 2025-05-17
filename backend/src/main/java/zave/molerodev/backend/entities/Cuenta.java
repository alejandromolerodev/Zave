package zave.molerodev.backend.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String tipo;

    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")  // clave foránea a Usuario
    private Usuario usuario;

    // Nota: Quitamos la referencia a Usuario para romper ciclo

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
