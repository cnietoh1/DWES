package es.calosnh.examendwes.servicios;

import es.calosnh.examendwes.entidades.Cancion;
import es.calosnh.examendwes.repositorios.CancionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CancionService {

    private final CancionRepository repositorio;

    public List<Cancion> findAll() {
        return repositorio.findAll();
    }

    public Cancion save(Cancion c) {
        return repositorio.save(c);
    }

    public void addAll(List<Cancion> lista) {
        repositorio.saveAll(lista);
    }

    public Optional<Cancion> findById(Long id) {
        return repositorio.findById(id);
    }

    public static String unaccent(String src) {
        return Normalizer
                .normalize(src, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    public List<Cancion> findByTitulo(String filtro) {
        return repositorio.findByTituloContainsIgnoreCase(unaccent(filtro));
    }

    public void delete(Cancion c) {
        repositorio.delete(c);
    }
}
