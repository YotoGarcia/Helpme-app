package co.edu.iudigital.helmelud.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class UsuarioResponseDTO {

    Long id;

    String username;

    String nombre;

    String apellidos;

    @JsonProperty("fecha_nacimiento")
    LocalDate fechaNacimiento;

    @JsonProperty("Created_at")
    LocalDateTime createdAt;
}
