package co.edu.iudigital.helmelud.mappers;

import co.edu.iudigital.helmelud.dtos.requests.CasoRequestDTO;
import co.edu.iudigital.helmelud.dtos.response.CasoResponseDTO;
import co.edu.iudigital.helmelud.models.Caso;
import co.edu.iudigital.helmelud.models.Delito;
import co.edu.iudigital.helmelud.models.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CasoMapper {

    public Caso toCaso(CasoRequestDTO casoRequestDTO) {

        Caso caso = new Caso();
        caso.setFechaHora(casoRequestDTO.getFechaHora());
        caso.setAltitud(casoRequestDTO.getAltitud());
        caso.setLatitud(casoRequestDTO.getLatitud());
        caso.setDetalle(casoRequestDTO.getDetalle());
        caso.setUrlMap(casoRequestDTO.getUrlMap());
        caso.setRmiMap(casoRequestDTO.getRmiMap());
        caso.setLongitud(casoRequestDTO.getLongitud());
        return caso;

    }

    public Caso toCaso(CasoRequestDTO casoRequestDTO, Usuario usuario, Delito delito) {

        Caso caso = new Caso();
        caso.setFechaHora(casoRequestDTO.getFechaHora());
        caso.setAltitud(casoRequestDTO.getAltitud());
        caso.setLatitud(casoRequestDTO.getLatitud());
        caso.setDetalle(casoRequestDTO.getDetalle());
        caso.setUrlMap(casoRequestDTO.getUrlMap());
        caso.setRmiMap(casoRequestDTO.getRmiMap());
        caso.setLongitud(casoRequestDTO.getLongitud());

        caso.setDelito(delito);
        caso.setUsuario(usuario);

        return caso;
    }

    public CasoResponseDTO toCasoResponseDTO(Caso caso) {
        return CasoResponseDTO.builder()
                .id(caso.getId())
                .fechaHora(caso.getFechaHora())
                .latitud(caso.getLatitud())
                .longitud(caso.getLongitud())
                .altitud(caso.getAltitud())
                .detalle(caso.getDetalle())
                .urlMap(caso.getUrlMap())
                .rmiMap(caso.getRmiMap())
                .delito(caso.getDelito().getNombre())
                .createdAt(caso.getCreatedAt())
                .usuario(caso.getUsuario().getUsername())
                .isVisible(caso.getIsVisible())
                .build();
    }

    public List<CasoResponseDTO> toCasoResponseDTOs(List<Caso> casos) {
        List<CasoResponseDTO> casoResponseDTOS =
                casos.stream()
                    .map(caso -> toCasoResponseDTO(caso))
                    .collect(Collectors.toList());
        return casoResponseDTOS;
    }
}
