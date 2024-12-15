package co.edu.iudigital.helmelud.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorDTO implements Serializable {

    static final long serialVersionUID = 1L;

    String error;
    String message;
    int status;
    LocalDateTime date;

    public static ErrorDTO errorDto(String error, String message, int status) {
        return ErrorDTO.builder()
                .error(error)
                .message(message)
                .status(status)
                .date(LocalDateTime.now())
                .build();
    }
}

