package cl.labs.conversion.repository;

import cl.labs.conversion.model.ConversionJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversionJobRepository extends JpaRepository<ConversionJob, Long> {
}
