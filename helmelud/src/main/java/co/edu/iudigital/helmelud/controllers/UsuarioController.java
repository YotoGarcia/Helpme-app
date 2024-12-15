package co.edu.iudigital.helmelud.controllers;

import co.edu.iudigital.helmelud.dtos.requests.UsuarioRequestDTO;
import co.edu.iudigital.helmelud.dtos.response.UsuarioResponseDTO;
import co.edu.iudigital.helmelud.exceptions.RestException;
import co.edu.iudigital.helmelud.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/signup")
    public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody UsuarioRequestDTO usuarioRequestDTO)
            throws RestException {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.registrar(usuarioRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponseDTO);
    }

}
