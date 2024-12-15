package co.edu.iudigital.helmelud.services.impl;

import co.edu.iudigital.helmelud.dtos.ErrorDTO;
import co.edu.iudigital.helmelud.dtos.requests.CasoRequestDTO;
import co.edu.iudigital.helmelud.dtos.response.CasoResponseDTO;
import co.edu.iudigital.helmelud.exceptions.NotFoundException;
import co.edu.iudigital.helmelud.mappers.CasoMapper;
import co.edu.iudigital.helmelud.models.Caso;
import co.edu.iudigital.helmelud.models.Delito;
import co.edu.iudigital.helmelud.models.Usuario;
import co.edu.iudigital.helmelud.repositories.ICasoRepository;
import co.edu.iudigital.helmelud.repositories.IDelitoRepository;
import co.edu.iudigital.helmelud.repositories.IUsuarioRepository;
import co.edu.iudigital.helmelud.security.UtilService;
import co.edu.iudigital.helmelud.services.ICasoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
public class CasoServiceImpl implements ICasoService {

    @Autowired
    private ICasoRepository casoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IDelitoRepository delitoRepository;

    @Autowired
    private CasoMapper casoMapper;

    @Autowired
    private UtilService utilService;

    @Override
    public CasoResponseDTO crearCaso(CasoRequestDTO casoRequestDTO) throws NotFoundException {

        final String username = utilService.obtenerUsuarioActual();

        Usuario usuario = validarUsuario(username);

        Delito delito = validarDelito(casoRequestDTO.getDelitoId());

        Caso caso = casoMapper.toCaso(casoRequestDTO, usuario, delito);

        caso = casoRepository.save(caso);

        return casoMapper.toCasoResponseDTO(caso);

    }

    private Usuario validarUsuario(String username) throws NotFoundException {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(ErrorDTO.errorDto(
                        "Usuario",
                        "No existe Usuario con Id: " + username,
                        HttpStatus.NOT_FOUND.value()
                )));
    }

    private Delito validarDelito(Long delitoId) throws NotFoundException {
        return delitoRepository.findById(delitoId)
                .orElseThrow(() -> new NotFoundException(ErrorDTO.errorDto(
                        "Delito",
                        "No existe delito con Id: " + delitoId,
                        HttpStatus.NOT_FOUND.value()
                )));
    }

    @Override
    public CasoResponseDTO consultarCasoPorId(Long id) throws NotFoundException {
        Caso casoOpt  =  casoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorDTO.builder()
                        .error("Delito")
                        .message("No extiste delito con Id: " + id)
                        .status(HttpStatus.NOT_FOUND.value())
                        .date(LocalDateTime.now())
                        .build()
                ));

        return casoMapper.toCasoResponseDTO(casoOpt);

    }

    @Override
    public List<CasoResponseDTO> consultarCasosVisibles() {
        List<Caso> casos = casoRepository.findByIsVisibleTrue();
        return casoMapper.toCasoResponseDTOs(casos);
    }

    @Override
    public List<CasoResponseDTO> consultarCasos() {
        List<Caso> casos = casoRepository.findAll();
        return casoMapper.toCasoResponseDTOs(casos);

    }

    @Override
    public CasoResponseDTO inhabilitarCaso(Long id) {
        Caso caso = casoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Caso no existe"));
        caso.setIsVisible(false);
        caso = casoRepository.save(caso);
        return casoMapper.toCasoResponseDTO(caso);
    }
}
