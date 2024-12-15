package co.edu.iudigital.helmelud.exceptions;

import co.edu.iudigital.helmelud.dtos.ErrorDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestException extends Exception {

    private ErrorDTO errorDto;

    public RestException() {super();}

    public RestException(ErrorDTO errorDto) {
        super(errorDto.getError());
        this.errorDto = errorDto;
    }

    public RestException(String msg) {super(msg);}

    public RestException(String msg, Exception ex) {super(msg, ex);}
}
