package co.edu.iudigital.helmelud.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "casos")
public class Caso implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "fecha_hora")
    LocalDateTime fechaHora;

    @Column
    Float latitud;

    @Column
    Float longitud;

    @Column
    Float altitud;

    @Column (name = "is_visible")
    Boolean isVisible;

    @Column
    String detalle;

    @Column (name = "url_map")
    String urlMap;

    @Column(name = "rmi_map")
    String rmiMap;

    @ManyToOne
    @JoinColumn(name = "delitos_id")
    Delito delito;

    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    Usuario usuario;

    @Column (name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @Column (name = "updated_at")
    LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if(isVisible == null) {
            isVisible = true;
        }
    }

    @PreUpdate
    public void preUpdate() {
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
    }

}
