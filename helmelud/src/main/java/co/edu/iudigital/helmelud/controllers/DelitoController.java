package co.edu.iudigital.helmelud.controllers;

import co.edu.iudigital.helmelud.exceptions.NotFoundException;
import co.edu.iudigital.helmelud.models.Delito;
import co.edu.iudigital.helmelud.services.impl.DelitoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/delitos")
public class DelitoController {

    @Autowired
    private DelitoServiceImpl delitoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Delito> guardarDelito(@RequestBody Delito delito) {
        Delito delitoResponse = delitoService.crearDelito(delito);
        return ResponseEntity.status(HttpStatus.CREATED).body(delitoResponse);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<Delito>> consultarTodos() {
        List<Delito> delitos = delitoService.obtenerDelitos();
        return ResponseEntity.ok(delitos);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<Delito> consultarPorId(@PathVariable Long id) throws NotFoundException {
        Delito delito = delitoService.obtenerDelitoPorId(id);
        return ResponseEntity.ok(delito);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Delito> actualizarPorId(@RequestBody Delito delito, @PathVariable Long id) {
        Delito delitoBD = delitoService.actualizarDelitoPorId(id, delito);
        return ResponseEntity.status(HttpStatus.OK).body(delitoBD);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void borrarPorId(@PathVariable Long id) {
        delitoService.borrarDelitoPorId(id);
    }

}
