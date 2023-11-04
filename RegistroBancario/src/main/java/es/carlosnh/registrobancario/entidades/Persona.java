package es.carlosnh.registrobancario.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    private String nombre;
    private String apellidos;
    private LocalDate fechaNac;
    private Genero genero;
    private EstadoCivil estadoCivil;
    private Nacionalidad nacionalidad;
    private Departamento departamento;
    private Long salario;
    private String comentarios;
    private String cuentaCorriente;
}
