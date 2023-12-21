package es.calosnh.examendwes.servicios;

import es.calosnh.examendwes.entidades.Genero;
import es.calosnh.examendwes.repositorios.GeneroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class GeneroService {

    private final GeneroRepository repositorio;

    public List<Genero> findAll() {
        return repositorio.findAll();
    }

    public Genero save(Genero g) { return repositorio.save(g); }

    public void addAll(List<Genero> lista) {
        repositorio.saveAll(lista);
    }

    public Optional<Genero> findById(Long id) {
        return repositorio.findById(id);
    }
    public Optional<Genero> findByNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    public void delete(Genero g) {
        repositorio.delete(g);
    }
}
