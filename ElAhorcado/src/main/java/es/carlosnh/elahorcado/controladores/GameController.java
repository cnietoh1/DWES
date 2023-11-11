package es.carlosnh.elahorcado.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class GameController {
    private List<String> palabras = Arrays.asList("java", "spring", "thymeleaf", "ahorcado", "vue", "angular", "react", "javascript", "frontend", "backend");
    private String palabra;
    private char[] adivinarPalabra;
    private int intentos;

    @GetMapping("/")
    public String index(Model model) {
        empezarJuego();
        model.addAttribute("adivinarPalabra", String.valueOf(adivinarPalabra));
        model.addAttribute("intentos", intentos);
        return "index";
    }

    @PostMapping("/adivinar")
    public String adivinarPalabra(@RequestParam char letter, Model model) {
        boolean respuesta = letrasAdivinadas(letter);
        model.addAttribute("adivinarPalabra", String.valueOf(adivinarPalabra));
        model.addAttribute("intentos", intentos);

        if (intentos == 0 || !Arrays.toString(adivinarPalabra).contains("_")) {
            model.addAttribute("finJuego", true);
        } else {
            dibujarMuneco(model);
        }

        return "index";
    }

    private void empezarJuego() {
        Random random = new Random();
        palabra = palabras.get(random.nextInt(palabras.size()));
        adivinarPalabra = new char[palabra.length()];
        Arrays.fill(adivinarPalabra, '_');
        intentos = 6;
    }

    private boolean letrasAdivinadas(char letter) {
        boolean respuesta = false;

        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letter) {
                adivinarPalabra[i] = letter;
                respuesta = true;
            }
        }

        if (!respuesta) {
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

