package zave.molerodev.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zave.molerodev.backend.entities.Cuenta;
import zave.molerodev.backend.entities.Ingreso;
import zave.molerodev.backend.entities.Gasto;
import zave.molerodev.backend.repository.CuentaRepository;
import zave.molerodev.backend.repository.IngresoRepository;
import zave.molerodev.backend.repository.GastoRepository;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private IngresoRepository ingresoRepository;

    @Autowired
    private GastoRepository gastoRepository;

    // Obtener una cuenta específica por su id
    public Cuenta findById(Long cuentaId) {
        return cuentaRepository.findById(cuentaId).orElse(null); // Devuelve la cuenta si existe o null si no
    }

    // Obtener todas las cuentas de un usuario
    public List<Cuenta> findByUserId(Long userId) {
        return cuentaRepository.findByUsuarioId(userId);
    }

    // Obtener los ingresos de una cuenta específica
    public List<Ingreso> findIngresosByCuenta(Long cuentaId) {
        Cuenta cuenta = findById(cuentaId);
        if (cuenta != null) {
            return ingresoRepository.findByCuenta(cuenta);
        }
        return null;
    }

    // Obtener los gastos de una cuenta específica
    public List<Gasto> findGastosByCuenta(Long cuentaId) {
        Cuenta cuenta = findById(cuentaId);
        if (cuenta != null) {
            return gastoRepository.findByCuenta(cuenta);
        }
        return null;
    }
}
