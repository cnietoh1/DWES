package es.carlosnh.springboot_carlosnieto.servicios;

import es.carlosnh.springboot_carlosnieto.entidades.Perfil;
import es.carlosnh.springboot_carlosnieto.repositorios.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PerfilService {

	private final PerfilRepository repositorio;

	public List<Perfil> findAll() {
		return repositorio.findAll();
	}

	public Optional<Perfil> findByNombre(String nombre) { return repositorio.findByNombre(nombre);}
	public Perfil save(Perfil p) { return repositorio.save(p); }

}
