package es.carlosnh.registrobancario.controladores;

import es.carlosnh.registrobancario.entidades.Persona;
import es.carlosnh.registrobancario.servicios.PersonaService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class RegistroController {

    private final PersonaService service;

    private final HttpSession session;

    @GetMapping({"/", "/paso1"})
    public String formulario1(Model model, HttpSession session) {
        Persona persona = (Persona) session.getAttribute("persona");
        if (persona == null) {
            persona = new Persona();
        }
        model.addAttribute("persona", persona);
        return "form1";
    }

    @PostMapping({"/", "/paso1"})
    public String guardarFormulario1(Persona persona, HttpSession session) {
        session.setAttribute("persona", persona);
        return "redirect:paso1";
    }

    @GetMapping("/paso2")
    public String formulario2(Model model, HttpSession session) {
        Persona persona = (Persona) session.getAttribute("persona");
        if (persona == null) {
            persona = new Persona();
        }
        model.addAttribute("persona", persona);
        return "form2";
    }

    @PostMapping("/paso2")
    public String guardarFormulario2(Persona persona, HttpSession session) {
        session.setAttribute("persona", persona);
        return "redirect:paso2";
    }


    @GetMapping("/paso3")
    public String formulario3(Model model, HttpSession session) {
        Persona persona = (Persona) session.getAttribute("persona");
        if (persona == null) {
            persona = new Persona();
        }
        model.addAttribute("persona", persona);
        return "form3";
    }

    @PostMapping("/paso3")
    public String guardarFormulario3(Persona persona, HttpSession session) {
        session.setAttribute("persona", persona);
        return "redirect:paso3";
    }

    @GetMapping("/resumen")
    public String resumen(Model model, Persona persona, HttpSession session) {
        persona = (Persona) session.getAttribute("persona");
        model.addAttribute("persona", persona);
        return "form-final";
    }

    @PostMapping("/resumen")
    public String resumen(@ModelAttribute("persona") Persona persona, HttpSession session, Model model) {
        session.setAttribute("persona", persona);

        return "redirect:resumen";
    }
}
