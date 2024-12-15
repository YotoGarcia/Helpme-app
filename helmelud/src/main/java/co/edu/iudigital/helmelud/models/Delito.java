package co.edu.iudigital.helmelud.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "delitos")
public class Delito implements Serializable {

    static final long serialVersionUID = 1L;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank(message = "Nombre requerido")
    @Column(unique = true, length = 120, nullable = false)
    private String nombre;

    @Column
    private String descripcion;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "created_At", updatable = false)
    LocalDateTime creationAt;

    @PrePersist
    public void prePersist() {
        creationAt = LocalDateTime.now();
    }
}
