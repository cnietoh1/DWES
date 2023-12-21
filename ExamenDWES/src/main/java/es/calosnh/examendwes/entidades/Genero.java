package es.calosnh.examendwes.entidades;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "genero")
public class Genero {
    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String descripcion;


}
