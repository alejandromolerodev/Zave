package zave.molerodev.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zave.molerodev.backend.entities.Gasto;
import zave.molerodev.backend.entities.Cuenta;
import zave.molerodev.backend.repository.GastoRepository;

import java.util.List;

@Service
public class GastoService {

    @Autowired
    private GastoRepository gastoRepository;

    public List<Gasto> findByCuenta(Cuenta cuenta) {
        return gastoRepository.findByCuenta(cuenta);
    }
   
    public Gasto save(Gasto gasto) {
        return gastoRepository.save(gasto);
    }
}
