package cl.labs.conversion.repository;

import cl.labs.conversion.model.JobFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobFileRepository extends JpaRepository<JobFile, Long> {
}
