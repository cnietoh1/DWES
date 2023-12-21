package es.calosnh.examendwes.repositorios;

import es.calosnh.examendwes.entidades.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CancionRepository extends JpaRepository<Cancion,Long> {
    public List<Cancion> findByTituloStartingWithIgnoreCase (String titulo);
    public List<Cancion> findByTituloContainsIgnoreCase(String filtro);

}
