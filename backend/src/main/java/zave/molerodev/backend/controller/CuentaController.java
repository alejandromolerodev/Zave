package zave.molerodev.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import zave.molerodev.backend.entities.Cuenta;
import zave.molerodev.backend.entities.Ingreso;
import zave.molerodev.backend.entities.Gasto;
import zave.molerodev.backend.service.IngresoService;
import zave.molerodev.backend.service.CuentaService;
import zave.molerodev.backend.service.GastoService;
import zave.molerodev.backend.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/zave/usuario")
public class CuentaController {


    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private GastoService gastoService;

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

    // Crear un nuevo ingreso
    @PostMapping("/ingresos/{cuentaId}")
    public ResponseEntity<Ingreso> agregarIngreso(
            @PathVariable Long cuentaId, @RequestBody Ingreso ingreso) {
        Cuenta cuenta = cuentaService.findById(cuentaId);
        if (cuenta == null) {
            return ResponseEntity.notFound().build();
        }

        ingreso.setCuenta(cuenta);  // Asociar ingreso con cuenta
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

        gasto.setCuenta(cuenta);  // Asociar gasto con cuenta
        Gasto nuevoGasto = gastoService.save(gasto);
        return ResponseEntity.ok(nuevoGasto);
    }
}
