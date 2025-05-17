package zave.molerodev.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import zave.molerodev.backend.entities.Cuenta;
import zave.molerodev.backend.entities.Ingreso;
import zave.molerodev.backend.entities.Usuario;
import zave.molerodev.backend.entities.Gasto;
import zave.molerodev.backend.service.IngresoService;
import zave.molerodev.backend.service.UserService;
import zave.molerodev.backend.service.CuentaService;
import zave.molerodev.backend.service.GastoService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/zave/cuenta")
@CrossOrigin(origins = "http://localhost:4200")
public class CuentaController {


    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private GastoService gastoService;


    @Autowired
    private UserService userService;

    // Obtener todos los ingresos de una cuenta específica de un usuario
    @GetMapping("/ingresos/{cuentaId}")
    public ResponseEntity<List<Ingreso>> getIngresosDeCuenta(@PathVariable Long cuentaId) {
        Cuenta cuenta = cuentaService.findById(cuentaId);
        if (cuenta == null) {
            return ResponseEntity.notFound().build();
        }

        List<Ingreso> ingresos = ingresoService.findByCuenta(cuenta);
        return ResponseEntity.ok(ingresos);
    }

    // Obtener todos los gastos de una cuenta específica de un usuario
    @GetMapping("/gastos/{cuentaId}")
    public ResponseEntity<List<Gasto>> getGastosDeCuenta(@PathVariable Long cuentaId) {
        Cuenta cuenta = cuentaService.findById(cuentaId);
        if (cuenta == null) {
            return ResponseEntity.notFound().build();
        }

        List<Gasto> gastos = gastoService.findByCuenta(cuenta);
        return ResponseEntity.ok(gastos);
    }

    // Obtener saldo de una cuenta específica
    @GetMapping("/saldo/{cuentaId}")
    public ResponseEntity<BigDecimal> getSaldoDeCuenta(@PathVariable Long cuentaId) {
        Cuenta cuenta = cuentaService.findById(cuentaId);
        if (cuenta == null) {
            return ResponseEntity.notFound().build();
        }

         BigDecimal saldo = cuenta.getSaldo();
        return ResponseEntity.ok(saldo);
    }

    // Crear un nuevo ingreso
    @PostMapping("/ingresos/{cuentaId}")
    public ResponseEntity<Ingreso> agregarIngreso(
            @PathVariable Long cuentaId, @RequestBody Ingreso ingreso) {
        Cuenta cuenta = cuentaService.findById(cuentaId);
        if (cuenta == null) {
            return ResponseEntity.notFound().build();
        }
    
        ingreso.setCuenta(cuenta); // Asocia el ingreso a la cuenta
    
        // Sumar el importe del ingreso al saldo
        cuenta.setSaldo(cuenta.getSaldo().add(ingreso.getImporte()));
        cuentaService.save(cuenta); // Guardar el nuevo saldo
    
        Ingreso nuevoIngreso = ingresoService.save(ingreso);
        return ResponseEntity.ok(nuevoIngreso);
    }
    

    // Crear un nuevo gasto
    @PostMapping("/gastos/{cuentaId}")
    public ResponseEntity<Gasto> agregarGasto(
            @PathVariable Long cuentaId, @RequestBody Gasto gasto) {
        Cuenta cuenta = cuentaService.findById(cuentaId);
        if (cuenta == null) {
            return ResponseEntity.notFound().build();
        }

        gasto.setCuenta(cuenta); // Asocia el gasto a la cuenta
        cuenta.setSaldo(cuenta.getSaldo().subtract(gasto.getImporte()));
        Gasto nuevoGasto = gastoService.save(gasto);
        return ResponseEntity.ok(nuevoGasto);
    }


    @PostMapping("/usuario/{userId}")
    public ResponseEntity<Cuenta> crearCuenta(@PathVariable Long userId, @RequestBody Cuenta cuenta) {
        Usuario usuario = userService.findById(userId); // Busca al usuario una sola vez
        if (usuario == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 si el usuario no existe
        }

        System.out.println("Usuario encontrado: " + usuario.getUsername());
        System.out.println("Creando cuenta para el usuario: " + userId);

        cuenta.setUsuario(usuario); // Asocia la cuenta al usuario
        Cuenta nuevaCuenta = cuentaService.save(cuenta); // Guarda la cuenta
        return ResponseEntity.ok(nuevaCuenta); // Retorna la cuenta creada


    }

    @GetMapping("/cuentas/{userId}")
    public ResponseEntity<List<Cuenta>> obtenerCuentasPorUsuario(@PathVariable Long userId) {
        Usuario usuario = userService.findById(userId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        List<Cuenta> cuentas = cuentaService.findByUserId(userId);
        return ResponseEntity.ok(cuentas);
    }
}
