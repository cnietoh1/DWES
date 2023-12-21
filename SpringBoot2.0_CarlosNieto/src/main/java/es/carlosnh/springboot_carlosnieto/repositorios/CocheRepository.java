package es.carlosnh.springboot_carlosnieto.repositorios;

import es.carlosnh.springboot_carlosnieto.entidades.Coche;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CocheRepository extends JpaRepository<Coche,Long> {

    public List<Coche> findByNombreStartingWithIgnoreCase (String nombre);

    public List<Coche> findByNombreContainsIgnoreCase (String filtro);
}
