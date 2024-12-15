package co.edu.iudigital.helmelud.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;
import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class UsuarioRequestDTO implements Serializable        {

    static final long serialVersionUID = 1L;

    String username;

    String nombre;

    String apellidos;

    String password;

    @JsonProperty("fecha_nacimiento")
    LocalDate fechaNacimiento;

}
