package co.edu.iudigital.helmelud.services;

import co.edu.iudigital.helmelud.dtos.requests.CasoRequestDTO;
import co.edu.iudigital.helmelud.dtos.response.CasoResponseDTO;
import co.edu.iudigital.helmelud.exceptions.NotFoundException;

import java.util.List;

public interface ICasoService  {
    CasoResponseDTO crearCaso(CasoRequestDTO casoRequestDTO) throws NotFoundException;

    CasoResponseDTO consultarCasoPorId(Long Id) throws NotFoundException;

    List<CasoResponseDTO> consultarCasosVisibles();

    List<CasoResponseDTO> consultarCasos();

    CasoResponseDTO inhabilitarCaso(Long Id);
}
