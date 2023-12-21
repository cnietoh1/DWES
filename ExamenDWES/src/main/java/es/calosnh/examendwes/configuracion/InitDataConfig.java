package es.calosnh.examendwes.configuracion;

import es.calosnh.examendwes.entidades.Cancion;
import es.calosnh.examendwes.entidades.Genero;
import es.calosnh.examendwes.entidades.Valoracion;
import es.calosnh.examendwes.servicios.CancionService;
import es.calosnh.examendwes.servicios.GeneroService;
import es.calosnh.examendwes.storage.StorageService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Slf4j
@Configuration
public class InitDataConfig {
    @Autowired
    private StorageService storageService;
    @Autowired
    private  CancionService cancionService;
    @Autowired
    private  GeneroService generoService;


    @PostConstruct
    public void initStorage() {
        storageService.deleteAll();
        storageService.init();
    }

    @PostConstruct
    public void initCancion() {
        Genero clasica = Genero.builder().nombre("CLASICA").build();
        Genero flamenco = Genero.builder().nombre("FLAMENCO").build();
        Genero rock = Genero.builder().nombre("ROCK").build();
        Genero pop_urban = Genero.builder().nombre("POP_URBAN").build();
        Genero trap = Genero.builder().nombre("TRAP").build();

        generoService.addAll (Arrays.asList (clasica, flamenco, rock, pop_urban, trap ));

        cancionService.addAll(
                Arrays.asList (
                        Cancion.builder()
                                //.id(1L)
                                .titulo("Thunderstuck")
                                .nombreArtista("AC-DC")
                                .duracion(3L)
                                .fechaPubli(LocalDate.of(2010,1,1))
                                .numeroRepro(456L)
                                .valoracion(Valoracion.BUENA)
                                .genero(rock)
                                .like(true)
                                .build(),

                        Cancion.builder()
                                //.id(2L)
                                .titulo("Gg4")
                                .nombreArtista("Chucky")
                                .duracion(4L)
                                .fechaPubli(LocalDate.of(2018,7,14))
                                .numeroRepro(77L)
                                .valoracion(Valoracion.MEH)
                                .genero(trap)
                                .like(false)
                                .build(),

                        Cancion.builder()
                                //.id(3L)
                                .titulo("Saturno")
                                .nombreArtista("Pablo Alboran")
                                .duracion(3L)
                                .fechaPubli(LocalDate.of(2020,5,8))
                                .numeroRepro(30000L)
                                .valoracion(Valoracion.MUYFAN)
                                .genero(pop_urban)
                                .like(true)
                                .build()
                ));
    }

}
