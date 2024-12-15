package co.edu.iudigital.helmelud.services;

import co.edu.iudigital.helmelud.dtos.requests.UsuarioRequestDTO;
import co.edu.iudigital.helmelud.dtos.requests.UsuarioRequestUpdateDTO;
import co.edu.iudigital.helmelud.dtos.response.UsuarioResponseDTO;
import co.edu.iudigital.helmelud.exceptions.RestException;
import co.edu.iudigital.helmelud.models.Usuario;
import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUsuarioService {

    UsuarioResponseDTO registrar(UsuarioRequestDTO usuarioRequestDTO) throws RestException;


    Usuario findByUsername(String username) throws RestException;


    UsuarioResponseDTO consutarPorId(Long id) throws RestException;


    UsuarioResponseDTO consutarPorUsername() throws RestException;


    UsuarioResponseDTO actualizar(UsuarioRequestUpdateDTO usuarioRequestUpdateDTO) throws RestException; // TODO: AUTENTICACION


    UsuarioResponseDTO subirImagen(MultipartFile image) throws RestException;;


    Resource obtenerImagen(String nombre) throws RestException;;
}