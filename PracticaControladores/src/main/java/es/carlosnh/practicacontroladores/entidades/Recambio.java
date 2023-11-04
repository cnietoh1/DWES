package es.carlosnh.practicacontroladores.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Recambio {
    private int id;
    private String nombre;
    private LocalDate fechaFabricacion;
    private int precio;
    private TipoVehiculo tipo;

}
