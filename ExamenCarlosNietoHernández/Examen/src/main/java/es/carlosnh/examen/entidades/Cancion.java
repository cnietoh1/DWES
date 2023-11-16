package es.carlosnh.examen.entidades;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class Cancion {
    @Min(value=1, message = "{cancion.id.mayorquecero}")
    private Long id;
    @NotEmpty(message = "{cancion.titulo.obligatorio}")
    private String titulo;
    @NotEmpty(message = "{cancion.artista.obligatorio}")
    private String nombreArtista;
    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaPubli;
    private int duracion;
    @NotNull(message = "{cancion.tipo.vacio}")
    private TipoCancion tipo;
    public boolean like;

}
