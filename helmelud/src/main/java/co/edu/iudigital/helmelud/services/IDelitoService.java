package co.edu.iudigital.helmelud.services;

import co.edu.iudigital.helmelud.exceptions.NotFoundException;
import co.edu.iudigital.helmelud.models.Delito;

import java.util.List;

public interface IDelitoService {

    Delito crearDelito(Delito delito);

    List<Delito> obtenerDelitos();

    Delito obtenerDelitoPorId(Long Id) throws NotFoundException;

    Delito actualizarDelitoPorId(Long Id, Delito delito);

    void borrarDelitoPorId(Long Id);
}
