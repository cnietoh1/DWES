package es.carlosnh.springboot_carlosnieto.repositorios;

import es.carlosnh.springboot_carlosnieto.entidades.TipoCoche;
import es.carlosnh.springboot_carlosnieto.entidades.TipoCombustible;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoCocheRepository extends JpaRepository<TipoCoche,Long> {

    public Optional<TipoCombustible> findByNombre(String nombre);
}
