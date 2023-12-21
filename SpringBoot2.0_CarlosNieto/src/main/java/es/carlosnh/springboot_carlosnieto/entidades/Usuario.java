package es.carlosnh.springboot_carlosnieto.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String email;
  private String password;

  @CreatedDate
  private LocalDateTime fechaAlta;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  //@ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "usuario_perfil",
          joinColumns = @JoinColumn(
                  name = "usuario_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(
                  name = "perfil_id", referencedColumnName = "id"))
  private Set<Perfil> perfiles = new HashSet<>();

}
