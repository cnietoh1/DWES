package es.calosnh.examendwes.repositorios;

import es.calosnh.examendwes.entidades.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero,Long> {

    public Optional<Genero> findByNombre(String nombre);
}
