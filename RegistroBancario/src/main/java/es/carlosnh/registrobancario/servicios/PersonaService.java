package es.carlosnh.registrobancario.servicios;

import es.carlosnh.registrobancario.entidades.Persona;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    @Autowired
    private HttpSession session;

    public void añadirSesion() {
        Persona personaNueva = new Persona();
        session.setAttribute("personaNueva", personaNueva);
    }
}
