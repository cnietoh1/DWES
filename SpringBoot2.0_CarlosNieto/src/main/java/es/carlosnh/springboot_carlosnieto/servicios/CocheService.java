package es.carlosnh.springboot_carlosnieto.servicios;

import es.carlosnh.springboot_carlosnieto.entidades.Coche;
import es.carlosnh.springboot_carlosnieto.repositorios.CocheRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CocheService {

    private final CocheRepository repositorio;

    public List<Coche> findAll() {
        return repositorio.findAll();
    }

    public Coche save(Coche c) { return repositorio.save(c); }

    public void saveAll(List<Coche> lista) {
        repositorio.saveAll(lista);
    }

    public Optional<Coche> findById(Long id) {
        return repositorio.findById(id);
    }

    /**
     * Quita los acentos a un string.
     * También se puede usar la librería StringUtils --> StringUtils.stripAccents
     * @param src
     * @return
     */
    public static String unaccent(String src) {
        return Normalizer
                .normalize(src, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    public List<Coche> findByNombre(String filtro) {
        return repositorio.findByNombreContainsIgnoreCase(unaccent(filtro));
    }

    public void delete(Coche c) {
        repositorio.delete(c);
    }
}
