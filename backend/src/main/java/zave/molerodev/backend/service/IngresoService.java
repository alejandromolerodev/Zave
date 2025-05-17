package zave.molerodev.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zave.molerodev.backend.entities.Ingreso;
import zave.molerodev.backend.entities.Cuenta;
import zave.molerodev.backend.repository.IngresoRepository;

import java.util.List;

@Service
public class IngresoService {

    @Autowired
    private IngresoRepository ingresoRepository;

    public List<Ingreso> findByCuenta(Cuenta cuenta) {
        return ingresoRepository.findByCuenta(cuenta);
    }
   
    public Ingreso save(Ingreso ingreso) {
        return ingresoRepository.save(ingreso);
    }
}
