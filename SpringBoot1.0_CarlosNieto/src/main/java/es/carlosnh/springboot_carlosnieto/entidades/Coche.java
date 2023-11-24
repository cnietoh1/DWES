package es.carlosnh.springboot_carlosnieto.entidades;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coche {
    @Min(value=1, message = "{mascota.id.mayorquecero}")
    private Long id;
    @NotEmpty
    private String marca;
    @NotEmpty
    private String modelo;
    private int cv;
    private Long numBastidor;
    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaMatriculacion;
    private TipoCoche tipoCoche;
    private TipoCombustible tipoCombustible;
    private String versionMotor;
    private int km;



}
