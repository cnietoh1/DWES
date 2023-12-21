package es.calosnh.examendwes.entidades;

import jakarta.persistence.*;
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
public class Cancion {
    @Id @GeneratedValue
    //@Min(value=1, message = "{cancion.id.mayorquecero}")
    private Long id;

    @NotEmpty
    private String titulo;

    private String nombreArtista;

    private Long duracion;

    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaPubli;

    private Long numeroRepro;

    private Valoracion valoracion;

    @ManyToOne
    private Genero genero;
    public boolean like;
    private String foto;

}
