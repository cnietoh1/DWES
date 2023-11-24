package es.carlosnh.springboot_carlosnieto.servicios;

import es.carlosnh.springboot_carlosnieto.entidades.Coche;
import es.carlosnh.springboot_carlosnieto.entidades.TipoCoche;
import es.carlosnh.springboot_carlosnieto.entidades.TipoCombustible;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CocheService {

    private List<Coche> repositorio = new ArrayList<>();

    public List<Coche> findAll() {
        return repositorio;
    }

    public Coche add(Coche c) {
        repositorio.add(c);
        return c;
    }

    public void addAll(List<Coche> lista) {
        repositorio.addAll(lista);
    }

    public Coche findById(Long id) {
        Coche coche = repositorio.stream()
                .filter(c -> id.equals(c.getId()))
                .findAny()
                .orElse(null);
        return coche;
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

    public List<Coche> findByName(String filtro) {

        List<Coche> result = repositorio.stream()
                .filter(coche -> unaccent(coche.getMarca().toLowerCase())
                        .startsWith(unaccent(filtro.toLowerCase())))
                .collect(Collectors.toList());
        return result;
    }

    public Coche edit(Coche c) {
        log.info("Editando coche {}", c);
        Coche coche = findById(c.getId());
        log.info("Coche encontrado {}", coche);

        if (coche != null) {
            repositorio.set(repositorio.indexOf(coche), c);
        } else {
            repositorio.add(c);
        }
        return c;
    }

    public void delete(Coche c) {
        repositorio.remove(c);
    }
}
