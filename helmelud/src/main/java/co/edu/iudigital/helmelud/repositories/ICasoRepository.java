package co.edu.iudigital.helmelud.repositories;

import co.edu.iudigital.helmelud.models.Caso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICasoRepository extends JpaRepository<Caso, Long> {
    List<Caso> findByIsVisibleTrue();
}
