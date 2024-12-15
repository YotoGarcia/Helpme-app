package co.edu.iudigital.helmelud.dtos.requests;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class UsuarioRequestUpdateDTO implements Serializable {

        static final long serialVersionUID = 1L;

        String nombre;

        String apellidos;

        String password;

        @JsonProperty("fecha_nacimiento")
        LocalDate fechaNacimiento;
    }

