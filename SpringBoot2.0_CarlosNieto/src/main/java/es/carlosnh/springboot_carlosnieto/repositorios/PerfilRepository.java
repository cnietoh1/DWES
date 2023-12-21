package es.carlosnh.springboot_carlosnieto.repositorios;

import es.carlosnh.springboot_carlosnieto.entidades.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil,Long> {

    public Optional<Perfil> findByNombre(String nombre);

}
