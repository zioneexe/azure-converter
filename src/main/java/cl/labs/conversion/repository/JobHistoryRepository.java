package cl.labs.conversion.repository;

import cl.labs.conversion.model.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
}
