package es.carlosnh.springboot_carlosnieto.servicios;

import es.carlosnh.springboot_carlosnieto.entidades.Coche;
import es.carlosnh.springboot_carlosnieto.entidades.TipoCoche;
import es.carlosnh.springboot_carlosnieto.entidades.TipoCombustible;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CocheService {

    private List<Coche> repositorio = new ArrayList<>();

    public List<Coche> findAll() {return repositorio;}

    public Coche add(Coche c) {
        repositorio.add(c);
        return c;
    }

    public Coche findById(Long id) {
        Coche coche = repositorio.stream()
                .filter(c -> id.equals(c.getId()))
                .findAny()
                .orElse(null);
        return coche;
    }

    public Coche edit(Coche c) {
        Coche coche = findById(c.getId());

        if (coche != null) {
            repositorio.set(repositorio.indexOf(coche), c);
        } else {
            repositorio.add(c);
        }
        return c;
    }

    public void delete(Coche c) {
        repositorio.remove(c);
    }

    @PostConstruct
    public void init() {
        repositorio.addAll(
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

                new Coche (2L, "Honda", "Civic Type S", 140,
                            954810L, LocalDate.of(2008,6,21),
                            TipoCoche.COMPACTO, TipoCombustible.DIESEL,
                            "iCTDi 2.2", 200431
                ),
                new Coche (3L, "Audi", "Q7", 500,
                            674832L, LocalDate.of(2009,11,7),
                            TipoCoche.SUV, TipoCombustible.DIESEL,
                            "V12 TDI", 127541
                ),
                new Coche (4L, "BMW", "M5 E60", 507,
                            111771L, LocalDate.of(2007,7,8),
                            TipoCoche.BERLINA, TipoCombustible.GASOLINA,
                            "V10 5.0", 84510
                )
            ));
    }
}
