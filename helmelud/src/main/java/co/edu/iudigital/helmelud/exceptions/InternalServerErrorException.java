package co.edu.iudigital.helmelud.exceptions;

import co.edu.iudigital.helmelud.dtos.ErrorDTO;

public class InternalServerErrorException extends RestException {

    private static final long serialVersionUID = 1L;

    private String codigoError;

    public InternalServerErrorException(String msg, String codigoError, Exception ex) {
        super(msg, ex);
        this.codigoError = codigoError;
    }

    public InternalServerErrorException(String msg, Exception ex) {super(msg, ex);}

    public InternalServerErrorException() {super();}

    public InternalServerErrorException(ErrorDTO errorDto) {super(errorDto);}

    public String getCodigoError() {return codigoError;}
}
