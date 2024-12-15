package co.edu.iudigital.helmelud.services.impl;

import co.edu.iudigital.helmelud.dtos.ErrorDTO;
import co.edu.iudigital.helmelud.dtos.requests.UsuarioRequestDTO;
import co.edu.iudigital.helmelud.dtos.requests.UsuarioRequestUpdateDTO;
import co.edu.iudigital.helmelud.dtos.response.UsuarioResponseDTO;
import co.edu.iudigital.helmelud.exceptions.BadRequestException;
import co.edu.iudigital.helmelud.exceptions.NotFoundException;
import co.edu.iudigital.helmelud.exceptions.RestException;
import co.edu.iudigital.helmelud.mappers.UsuarioMapper;
import co.edu.iudigital.helmelud.models.Role;
import co.edu.iudigital.helmelud.models.Usuario;
import co.edu.iudigital.helmelud.repositories.IRoleRepository;
import co.edu.iudigital.helmelud.repositories.IUsuarioRepository;
import co.edu.iudigital.helmelud.security.UtilService;
import co.edu.iudigital.helmelud.services.IEmailService;
import co.edu.iudigital.helmelud.services.IUsuarioService;
import co.edu.iudigital.helmelud.utils.Constants;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private UtilService utilService;

    @Override
    public UsuarioResponseDTO registrar(UsuarioRequestDTO usuarioRequestDTO) throws RestException, NotFoundException {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(usuarioRequestDTO.getUsername());
        if (usuarioOpt.isPresent()) {
            throw new BadRequestException(
                    ErrorDTO.builder()
                            .error("Error Usuario")
                            .message("Usuario ya existe")
                            .status(HttpStatus.BAD_REQUEST.value())
                            .date(LocalDateTime.now())
                            .build()
            );
        }


        Usuario usuario = usuarioMapper.toUsuario(usuarioRequestDTO);

        Role role = roleRepository.findByNombre(Constants.ROLE_USER).orElseThrow(
                () -> new NotFoundException(ErrorDTO.errorDto(
                        "Error de Rol", "No existe rol" + Constants.ROLE_USER, HttpStatus.NOT_FOUND.value()
                ))
        );

        usuario.setRoles(Collections.singletonList(role));
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        usuario = usuarioRepository.save(usuario);// TODO: MANIPULAR ERROR, AQUI PUEDE OCURRIR UNA EXCEPCION

        // TODO: COLOCARLO VOID Y ASYNCRONO
        emailService.sendEmail(
                "Te has registrado exitosamente en HelmeIUD con contraseña: " + usuarioRequestDTO.getPassword(),
                usuario.getUsername(),
                "Dada de Alta en HelpmeIUD"
        );

        return usuarioMapper.toUsuarioResponseDTO(usuario);
    }

    @Override
    public Usuario findByUsername(String username) throws RestException {
        return usuarioRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException(ErrorDTO.errorDto(
                        "Error de Usuario", "No existe usuario" + username, HttpStatus.NOT_FOUND.value()
                )));
    }

    @Override
    public UsuarioResponseDTO consutarPorId(Long id) throws RestException {
        return null;
    }

    @Override
    public UsuarioResponseDTO consutarPorUsername() throws RestException { // usuario logueado
        final String username = utilService.obtenerUsuarioActual();
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException(ErrorDTO.errorDto(
                        "Error de Usuario", "No existe usuario" + username, HttpStatus.NOT_FOUND.value()
                )));
        return usuarioMapper.toUsuarioResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO actualizar(UsuarioRequestUpdateDTO usuarioRequestUpdateDTO) throws RestException {
        final String username = utilService.obtenerUsuarioActual();
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException(ErrorDTO.errorDto(
                        "Error de Usuario", "No existe usuario" + username, HttpStatus.NOT_FOUND.value()
                )));
        usuario.setNombre(usuarioRequestUpdateDTO.getNombre() != null ? usuarioRequestUpdateDTO.getNombre() : usuario.getNombre());
        usuario.setApellidos(usuarioRequestUpdateDTO.getApellidos() != null ? usuarioRequestUpdateDTO.getApellidos() : usuario.getApellidos());
        usuario.setPassword(
                usuarioRequestUpdateDTO.getPassword() != null || usuarioRequestUpdateDTO.getPassword().length() > 0
                        ? passwordEncoder.encode(usuarioRequestUpdateDTO.getPassword()) : usuario.getPassword());
        usuario.setFechaNacimiento(
                usuarioRequestUpdateDTO.getFechaNacimiento() != null
                        ? usuarioRequestUpdateDTO.getFechaNacimiento() : usuario.getFechaNacimiento()
        );
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toUsuarioResponseDTO(usuario);
    }
    @Override
    public UsuarioResponseDTO subirImagen(MultipartFile image) throws RestException {
        return null;
    }

    @Override
    public Resource obtenerImagen(String nombre) throws RestException {
        return null;
    }
}