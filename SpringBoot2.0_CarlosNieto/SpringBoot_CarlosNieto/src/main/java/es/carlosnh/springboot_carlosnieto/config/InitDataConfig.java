package es.carlosnh.springboot_carlosnieto.config;

import es.carlosnh.springboot_carlosnieto.entidades.Coche;
import es.carlosnh.springboot_carlosnieto.entidades.TipoCoche;
import es.carlosnh.springboot_carlosnieto.entidades.TipoCombustible;
import es.carlosnh.springboot_carlosnieto.servicios.CocheService;
import es.carlosnh.springboot_carlosnieto.storage.StorageService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class InitDataConfig {

    @Autowired
    private StorageService storageService;

    @Autowired
    private CocheService cocheService;

    @PostConstruct
    public void initStorage() {
        storageService.deleteAll();
        storageService.init();
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
