package es.carlosnh.springboot_carlosnieto.servicios;

import es.carlosnh.springboot_carlosnieto.entidades.TipoCoche;
import es.carlosnh.springboot_carlosnieto.repositorios.TipoCocheRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TipoCocheService {

    private final TipoCocheRepository repositorio;

    public List<TipoCoche> findAll() {
        return repositorio.findAll();
    }

    public TipoCoche save(TipoCoche c) { return repositorio.save(c); }

    public void saveAll(List<TipoCoche> lista) {
        repositorio.saveAll(lista);
    }

    public Optional<TipoCoche> findById(Long id) {
        return repositorio.findById(id);
    }

    public void delete(TipoCoche c) {
        repositorio.delete(c);
    }
}
