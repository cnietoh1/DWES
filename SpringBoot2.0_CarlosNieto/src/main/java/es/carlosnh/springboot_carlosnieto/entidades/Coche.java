package es.carlosnh.springboot_carlosnieto.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coche")
public class Coche {
    @Id
    @GeneratedValue
    //@Min(value=1, message = "{mascota.id.mayorquecero}")
    private Long id;

    @NotEmpty
    @Column(nullable = false, unique = true, length=50)
    private String marca;
    @NotEmpty
    private String modelo;
    private int cv;
    private Long numBastidor;
    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaMatriculacion;
    @ManyToOne
    @JoinColumn(name = "tipo_coche_id")
    private TipoCoche tipoCoche;
    @ManyToOne
    @JoinColumn(name = "tipo_combustible_id")
    private TipoCombustible tipoCombustible;
    private String versionMotor;
    private int km;
    private String foto;



}
