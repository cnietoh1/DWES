package es.carlosnh.examen.servicios;

import es.carlosnh.examen.entidades.Cancion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CancionService {

    private List<Cancion> repositorio = new ArrayList<>();

    public List<Cancion> findAll() {
        return repositorio;
    }

    public Cancion add(Cancion c) {
        repositorio.add(c);
        return c;
    }
    public void addAll(List<Cancion> lista) {
        repositorio.addAll(lista);
    }

    public Cancion findById(Long id) {
        Cancion cancion = repositorio.stream()
                .filter(c -> id.equals(c.getId()))
                .findAny()
                .orElse(null);
        return cancion;
    }

    public static String unaccent(String src) {
        return Normalizer
                .normalize(src, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    public Cancion edit(Cancion c) {
        log.info("Editando cancion {}", c);
        Cancion cancion = findById(c.getId());
        log.info("Cancion encontrada {}", cancion);

        if (cancion != null) {
            repositorio.set(repositorio.indexOf(cancion), c);
        } else {
            repositorio.add(c);
        }

        return c;

    }

    public void delete(Cancion c) {
        repositorio.remove(c);
    }

    public Object findByName(String nombre) {
        Cancion cancion = repositorio.stream()
                .filter(c -> nombre.equals(c.getTitulo()))
                .findAny()
                .orElse(null);
        return cancion;
    }
}
