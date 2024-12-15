package co.edu.iudigital.helmelud.repositories;

import co.edu.iudigital.helmelud.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

        Optional<Role> findByNombre(String nombre);
    }

