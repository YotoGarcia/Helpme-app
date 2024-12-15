package co.edu.iudigital.helmelud.exceptions;

import co.edu.iudigital.helmelud.dtos.ErrorDTO;


public class UnauthorizedException extends RestException{

    private static final long serialVersionUID = 1L;

    public UnauthorizedException(){ super(); }

    public UnauthorizedException(ErrorDTO errorDto){
        super(errorDto); }
}
