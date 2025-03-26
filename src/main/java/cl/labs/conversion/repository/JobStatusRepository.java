package cl.labs.conversion.repository;

import cl.labs.conversion.model.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobStatusRepository extends JpaRepository<JobStatus, Long> {
}
