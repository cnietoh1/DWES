package es.carlosnh.springboot_carlosnieto.servicios;

import es.carlosnh.springboot_carlosnieto.entidades.Usuario;
import es.carlosnh.springboot_carlosnieto.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

	private final UsuarioRepository repositorio;

	private final PasswordEncoder passwordEncoder;

	public Usuario save(Usuario u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return repositorio.save(u);
	}

	public List<Usuario> saveAll (List<Usuario> lista) {
		lista.stream()
				.forEach(usuario ->
						usuario.setPassword(passwordEncoder.encode(usuario.getPassword())));
		return repositorio.saveAll(lista); }

	public Usuario findById(long id) {
		return repositorio.findById(id).orElse(null);
	}

	public Usuario buscarPorUsernameOEmail(String s) {
		return repositorio.buscarPorUsernameOEmail(s).orElse(null);
	}

	public Usuario findByUsernameOrEmail(String username, String email) {
		return repositorio.findByUsernameOrEmail(username,email).orElse(null);
	}

}
