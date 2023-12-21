package es.carlosnh.springboot_carlosnieto.entidades;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_combustible")
public class  TipoCombustible {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true, length=50)
    private String nombre;
    //GASOLINA, DIESEL, ELECTRICO, GLP
}
