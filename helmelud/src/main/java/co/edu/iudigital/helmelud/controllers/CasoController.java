package co.edu.iudigital.helmelud.controllers;

import co.edu.iudigital.helmelud.dtos.requests.CasoRequestDTO;
import co.edu.iudigital.helmelud.dtos.response.CasoResponseDTO;
import co.edu.iudigital.helmelud.exceptions.NotFoundException;
import co.edu.iudigital.helmelud.services.ICasoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/casos")
public class CasoController {

    @Autowired
    private ICasoService casoService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<CasoResponseDTO> crearCaso(@RequestBody CasoRequestDTO casoRequestDTO) throws NotFoundException {
        CasoResponseDTO responseDTO = casoService.crearCaso(casoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/visibles")
    public ResponseEntity<List<CasoResponseDTO>> consultarVisibles() {
        List<CasoResponseDTO> casos = casoService.consultarCasosVisibles();
        return ResponseEntity.ok(casos);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<CasoResponseDTO>> consultarTodos() {
        List<CasoResponseDTO> casos = casoService.consultarCasos();
        return ResponseEntity.ok(casos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CasoResponseDTO> consultarCasoPorId(@PathVariable Long id) throws NotFoundException {
        CasoResponseDTO caso = casoService.consultarCasoPorId(id);
        return ResponseEntity.ok(caso);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/visible/{is_visible}")
    public ResponseEntity<CasoResponseDTO> deshabilitar(@PathVariable final Long id){
        CasoResponseDTO caso = casoService.inhabilitarCaso(id);
        return ResponseEntity.ok(caso);
    }
}
