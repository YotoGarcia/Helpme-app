package co.edu.iudigital.helmelud.configs;

import co.edu.iudigital.helmelud.dtos.ErrorDTO;
import co.edu.iudigital.helmelud.exceptions.BadRequestException;
import co.edu.iudigital.helmelud.exceptions.InternalServerErrorException;
import co.edu.iudigital.helmelud.exceptions.NotFoundException;
import co.edu.iudigital.helmelud.exceptions.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({InternalServerErrorException.class})
    public ResponseEntity<ErrorDTO> getGeneralExceptions(InternalServerErrorException e){
        log.error(e.getMessage(),e);
        return new ResponseEntity<>(e.getErrorDto(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    @ResponseBody
    public ResponseEntity<ErrorDTO> getNotFoundRequest(NotFoundException e){
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getErrorDto(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class})
    @ResponseBody
    public ResponseEntity<ErrorDTO> getBadRequestException(BadRequestException e) {
        log.info(e.getErrorDto().getMessage());
        return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseBody
    public ResponseEntity<ErrorDTO> getUnauthorizedException(UnauthorizedException e) {
        log.info(e.getErrorDto().getMessage());
        return new ResponseEntity<>(e.getErrorDto(), HttpStatus.UNAUTHORIZED);
    }



}
