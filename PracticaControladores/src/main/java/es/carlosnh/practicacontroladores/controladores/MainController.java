package es.carlosnh.practicacontroladores.controladores;

import es.carlosnh.practicacontroladores.entidades.Recambio;
import es.carlosnh.practicacontroladores.entidades.TipoVehiculo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class MainController {

    @GetMapping({"/"})
    public String welcome(Model model) {
        model.addAttribute("titulo", "RECAMBIOS PURE SPORT");
        model.addAttribute("mensaje", "Nuestra empresa lleva en el sector desde el año 1995 y cuenta con más de 30 premios " +
                "y ha sido galardonada con el Premio Nacional de Tecnología e Innovacción.");
        return "index";
    }

    @GetMapping({"/que"})
    public String que(Model model) {
        Recambio recambio = new Recambio (1,
                "Bujías", LocalDate.of(2017,3,19),
                38, TipoVehiculo.COCHE);
        Recambio recambio2 = new Recambio (2,
                "Filtro del Aire", LocalDate.of(2013,9,28),
                15, TipoVehiculo.MOTOCICLETA);
        model.addAttribute("titulo", "RECAMBIOS PURE SPORT");
        model.addAttribute("descripcion", "Somos una empresa de recambios automovilísticos, " +
                "en nuestra web encontrará todo tipo de recambios para una amplia gama de modelos de las marcas líderes del sector.");
        model.addAttribute("recambio", recambio);
        model.addAttribute("recambio", recambio2);
        return "que";
    }

    @GetMapping({"/contacto"})
    public String contacto(Model model) {
        model.addAttribute("titulo", "RECAMBIOS PURE SPORT");
        model.addAttribute("contacto", "Contacto");
        model.addAttribute("email", "puresportsl@gmail.com");
        model.addAttribute("telefono","+34 655 431 166");
        model.addAttribute("fijo","+91 46 85 56");
        model.addAttribute("direccion","C/ Canal del Bósforo Nº8");
        return "contacto";
    }
}
