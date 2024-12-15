package co.edu.iudigital.helmelud.repositories;

import co.edu.iudigital.helmelud.models.Delito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public interface IDelitoRepository extends JpaRepository<Delito, Long> {

}
