package co.edu.iudigital.helmelud.dtos.response;

import co.edu.iudigital.helmelud.models.Delito;
import co.edu.iudigital.helmelud.models.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public class CasoResponseDTO {

    static final long serialVersionUID = 1L;


    Long id;

    @JsonProperty("fecha_hora")
    LocalDateTime fechaHora;

    Float latitud;

    Float longitud;

    Float altitud;

    @JsonProperty("is_visible")
    Boolean isVisible;

    String detalle;

    @JsonProperty("url_map")
    String urlMap;

    @JsonProperty("rmi_map")
    String rmiMap;

    @JsonProperty("nombre_delito")
    String delito;

    @JsonProperty("username")
    String usuario;

    @JsonProperty("create_at")
    LocalDateTime createdAt;

}
