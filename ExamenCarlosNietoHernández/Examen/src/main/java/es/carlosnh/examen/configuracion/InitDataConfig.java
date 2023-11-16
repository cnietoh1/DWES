package es.carlosnh.examen.configuracion;

import es.carlosnh.examen.entidades.Cancion;
import es.carlosnh.examen.entidades.TipoCancion;
import es.carlosnh.examen.servicios.CancionService;
import es.carlosnh.examen.storage.StorageService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class InitDataConfig {

    @Autowired
    private StorageService storageService;

    @Autowired
    private CancionService mascotaService;


    @PostConstruct
    public void initStorage() {
        storageService.deleteAll();
        storageService.init();
    }

    @PostConstruct
    public void initMascota() {
        mascotaService.addAll(
                Arrays.asList(
                        Cancion.builder()
                                .id(1L)
                                .titulo("Thunderstuck")
                                .nombreArtista("AC-DC")
                                .fechaPubli(LocalDate.of(2010,1,1))
                                .duracion(3)
                                .tipo(TipoCancion.ROCK)
                                .like(true)
                                .build(),

                        Cancion.builder()
                                .id(2L)
                                .titulo("Gg4")
                                .nombreArtista("Chucky")
                                .fechaPubli(LocalDate.of(2018,7,14))
                                .duracion(4)
                                .tipo(TipoCancion.TRAP)
                                .like(false)
                                .build(),

                        Cancion.builder()
                                .id(3L)
                                .titulo("Saturno")
                                .nombreArtista("Pablo Alboran")
                                .fechaPubli(LocalDate.of(2020,5,8))
                                .duracion(3)
                                .tipo(TipoCancion.POP_URBAN)
                                .like(true)
                                .build()
                ));

    }

}
