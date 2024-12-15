package co.edu.iudigital.helmelud.dtos.requests;
import co.edu.iudigital.helmelud.models.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class CasoRequestDTO implements Serializable {

    static final long serialVersionUID = 1L;

    @JsonProperty("fecha_hora")
    LocalDateTime fechaHora;

    Float latitud;

    Float longitud;

    Float altitud;

    String detalle;

    @JsonProperty("url_map")
    String urlMap;

    @JsonProperty("rmi_map")
    String rmiMap;

    @JsonProperty("delito_id")
    Long delitoId;

    @JsonIgnore
    Long usuarioId;

}