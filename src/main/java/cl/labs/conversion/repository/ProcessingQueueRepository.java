package cl.labs.conversion.repository;

import cl.labs.conversion.model.ProcessingQueue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessingQueueRepository extends JpaRepository<ProcessingQueue, Long> {
}
