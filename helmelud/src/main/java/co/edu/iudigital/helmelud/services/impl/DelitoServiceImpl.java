package co.edu.iudigital.helmelud.services.impl;

import co.edu.iudigital.helmelud.dtos.ErrorDTO;
import co.edu.iudigital.helmelud.exceptions.NotFoundException;
import co.edu.iudigital.helmelud.models.Delito;
import co.edu.iudigital.helmelud.repositories.IDelitoRepository;
import co.edu.iudigital.helmelud.services.IDelitoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DelitoServiceImpl implements IDelitoService {

    @Autowired
    private IDelitoRepository delitoRepository;

    @Transactional
    @Override
    public Delito crearDelito(Delito delito) {
        delito = delitoRepository.save(delito);
        return delito;
    }

    @Override
    public List<Delito> obtenerDelitos() {
        List<Delito> delitos = delitoRepository.findAll();
        return delitos;
    }

    @Override
    public Delito obtenerDelitoPorId(Long id) throws NotFoundException {
        Delito delito = delitoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorDTO.builder()
                        .error("Delito")
                        .message("No extiste delito con Id: " + id)
                        .status(HttpStatus.NOT_FOUND.value())
                        .date(LocalDateTime.now())
                        .build()
                ));
        return delito;
    }

    @Transactional
    @Override
    public Delito actualizarDelitoPorId(Long Id, Delito delito) {
        Delito delitoBD = delitoRepository.findById(Id).orElseThrow(() -> new RuntimeException("No existe delito"));
        delitoBD.setNombre(delito.getNombre() != null ? delito.getNombre() : delitoBD.getNombre());
        delitoBD.setDescripcion(delito.getDescripcion() != null ? delito.getDescripcion() : delitoBD.getDescripcion());
        delitoBD = delitoRepository.save(delitoBD);
        return delitoBD;
    }

    @Override
    public void borrarDelitoPorId(Long Id) {
        delitoRepository.deleteById(Id);
    }
}
