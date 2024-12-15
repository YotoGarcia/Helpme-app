package co.edu.iudigital.helmelud.mappers;

import co.edu.iudigital.helmelud.dtos.requests.UsuarioRequestDTO;
import co.edu.iudigital.helmelud.dtos.response.UsuarioResponseDTO;
import co.edu.iudigital.helmelud.models.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toUsuario(UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioRequestDTO.getUsername());
        usuario.setNombre(usuarioRequestDTO.getNombre());
        usuario.setApellidos(usuarioRequestDTO.getApellidos());
        usuario.setPassword(usuarioRequestDTO.getPassword());
        usuario.setFechaNacimiento(usuarioRequestDTO.getFechaNacimiento());
        return usuario;
    }

    public UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario){
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .fechaNacimiento(usuario.getFechaNacimiento())
                .createdAt(usuario.getCreatedAt())
                .build();
    }
}
