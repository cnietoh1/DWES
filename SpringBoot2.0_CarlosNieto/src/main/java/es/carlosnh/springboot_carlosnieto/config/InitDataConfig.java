package es.carlosnh.springboot_carlosnieto.config;

import es.carlosnh.springboot_carlosnieto.entidades.Coche;
import es.carlosnh.springboot_carlosnieto.entidades.TipoCoche;
import es.carlosnh.springboot_carlosnieto.entidades.TipoCombustible;
import es.carlosnh.springboot_carlosnieto.entidades.Usuario;
import es.carlosnh.springboot_carlosnieto.servicios.CocheService;
import es.carlosnh.springboot_carlosnieto.servicios.UsuarioService;
import es.carlosnh.springboot_carlosnieto.storage.StorageService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
public class InitDataConfig {

    //@Autowired
    private final StorageService storageService;
    //@Autowired
    private final CocheService cocheService;

    private final UsuarioService usuarioService;

    @PostConstruct
    public void initStorage() {
        storageService.deleteAll();
        storageService.init();
    }

    @PostConstruct
    public void initUsuarios() {
        Usuario usuario1 = Usuario.builder()
                .username("user")
                .email("user@mascot.es")
                .password("user")
                .role("ROLE_USER").build();

        usuario1 = usuarioService.save(usuario1);

        Usuario usuario2 = Usuario.builder()
                .username("admin")
                .email("admin@mascot.es")
                .password("admin")
                .role("ROLE_ADMIN").build();
        usuario2 = usuarioService.save(usuario2);
    }

    @PostConstruct
    public void initCoche() {
        cocheService.addAll(
                Arrays.asList(
                        Coche.builder()
                                .id(1L)
                                .marca("Volkswagen")
                                .modelo("Golf GTI")
                                .cv(200)
                                .numBastidor(175834L)
                                .fechaMatriculacion(LocalDate.of(2008,3,14))
                                .tipoCoche(TipoCoche.COMPACTO)
                                .tipoCombustible(TipoCombustible.GASOLINA)
                                .versionMotor("TFSI 2.0")
                                .km(54950)
                                .build(),

                        Coche.builder()
                                .id(2L)
                                .marca("Honda")
                                .modelo("Civic Type S")
                                .cv(140)
                                .numBastidor(954810L)
                                .fechaMatriculacion(LocalDate.of(2008,6,21))
                                .tipoCoche(TipoCoche.COMPACTO)
                                .tipoCombustible(TipoCombustible.DIESEL)
                                .versionMotor("iCTDi 2.2")
                                .km(200431)
                                .build(),

                        Coche.builder()
                                .id(3L)
                                .marca("Audi")
                                .modelo("Q7")
                                .cv(500)
                                .numBastidor(674832L)
                                .fechaMatriculacion(LocalDate.of(2009,11,7))
                                .tipoCoche(TipoCoche.SUV)
                                .tipoCombustible(TipoCombustible.DIESEL)
                                .versionMotor("V12 TDI")
                                .km(127541)
                                .build(),

                        Coche.builder()
                                .id(4L)
                                .marca("BMW")
                                .modelo("M5 E60")
                                .cv(507)
                                .numBastidor(111771L)
                                .fechaMatriculacion(LocalDate.of(2007,7,8))
                                .tipoCoche(TipoCoche.BERLINA)
                                .tipoCombustible(TipoCombustible.GASOLINA)
                                .versionMotor("V10 5.0")
                                .km(84510)
                                .build()
                ));
    }

}
