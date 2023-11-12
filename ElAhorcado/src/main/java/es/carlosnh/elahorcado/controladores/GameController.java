package es.carlosnh.elahorcado.controladores;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.security.Principal;
import java.util.*;

@Controller
public class GameController {
    // Lista de palabras para el juego
    private List<String> palabras = Arrays.asList("java", "spring", "thymeleaf", "ahorcado", "vue", "angular", "react", "javascript", "frontend", "backend");
    // Palabra seleccionada para el juego
    private String palabra;
    // Arreglo para mostrar las letras adivinadas
    private char[] adivinarPalabra;
    // Número de intentos restantes
    private int intentos;
    private Set<Character> letrasIntentadas = new HashSet<>();
    private Set<Character> letrasAcertadas = new HashSet<>();

    @GetMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        // Inicia un nuevo juego
        empezarJuego();
        model.addAttribute("adivinarPalabra", String.valueOf(adivinarPalabra));
        model.addAttribute("intentos", intentos);
        model.addAttribute("letrasIntentadas", letrasIntentadas);
        model.addAttribute("letrasAcertadas", letrasAcertadas);

        // Mostrar la palabra oculta solo para el usuario ADMIN
        if ("admin".equals(username)) {
            model.addAttribute("palabraOculta", palabra);
        }

        return "index";
    }

    @PostMapping("/adivinar")
    public String adivinarPalabra(@RequestParam char letter, Model model, Principal principal) {
        // Verificar si el juego ha finalizado
        if (intentos <= 0 || !Arrays.toString(adivinarPalabra).contains("_")) {
            model.addAttribute("error", "El juego ha finalizado. No puedes realizar más intentos.");
        } else {
            // Procesar la lógica del juego solo si no ha finalizado
            boolean respuesta = letrasAdivinadas(letter);
            model.addAttribute("adivinarPalabra", String.valueOf(adivinarPalabra));
            model.addAttribute("intentos", intentos);

            // Mostrar la palabra oculta solo para el usuario ADMIN
            if (isUserAdmin(principal)) {
                model.addAttribute("palabraOculta", palabra);
            }

            // Añadir las letras acertadas e intentadas al modelo
            model.addAttribute("letrasAcertadas", letrasAcertadas);
            model.addAttribute("letrasIntentadas", letrasIntentadas);

            // Lógica para mostrar el muñeco
            dibujarMuneco(model);

            // Verificar si se ha agotado los intentos o se ha adivinado la palabra
            if (intentos == 0) {
                model.addAttribute("finJuego", "GAME OVER");
            } else if (!Arrays.toString(adivinarPalabra).contains("_")) {
                model.addAttribute("finJuego", "HAS GANADO");
            }
        }

        return "index";
    }

    // Método para verificar si el usuario tiene el rol ADMIN
    private boolean isUserAdmin(Principal principal) {
        Authentication auth = (Authentication) principal;
        return auth.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
    }

    @PostMapping("/terminar")
    public String terminarPartida(Model model) {
        empezarJuego(); // Reinicia variables de juego

        // Redirige de nuevo a la página principal
        return "redirect:/";
    }

    @PostMapping("/local")
    public String cambiarLocal(@RequestParam String lang, HttpServletRequest request) {
        request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(lang));
        return "redirect:/";
    }

    private void empezarJuego() {
        Random random = new Random();
        palabra = palabras.get(random.nextInt(palabras.size()));
        adivinarPalabra = new char[palabra.length()];
        Arrays.fill(adivinarPalabra, '_');
        intentos = 6;
        letrasIntentadas.clear();
        letrasAcertadas.clear();
    }

    private boolean letrasAdivinadas(char letter) {
        boolean respuesta = false;

        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letter) {
                adivinarPalabra[i] = letter;
                respuesta = true;
            }
        }

        if (respuesta) {
            letrasAcertadas.add(letter);
        } else {
            letrasIntentadas.add(letter);
            intentos--;
        }

        return respuesta;
    }

    private void dibujarMuneco(Model model) {
        int partesRestantes = 6 - intentos;

        switch (partesRestantes) {
            case 1:
                model.addAttribute("muneco", "  O\n");
                break;
            case 2:
                model.addAttribute("muneco", "  O\n /");
                break;
            case 3:
                model.addAttribute("muneco", "  O\n /|\n");
                break;
            case 4:
                model.addAttribute("muneco", "  O\n /|\\\n");
                break;
            case 5:
                model.addAttribute("muneco", "  O\n /|\\\n / ");
                break;
            case 6:
                model.addAttribute("muneco", "  O\n /|\\\n / \\\n");
                break;
            default:
                model.addAttribute("muneco", "");
                break;
        }
    }
}

