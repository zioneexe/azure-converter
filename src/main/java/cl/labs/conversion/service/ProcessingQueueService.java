package cl.labs.conversion.service;

import cl.labs.conversion.model.ProcessingQueue;
import cl.labs.conversion.repository.ProcessingQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessingQueueService {

    @Autowired
    private ProcessingQueueRepository processingQueueRepository;

    public List<ProcessingQueue> getAllQueues() {
        return processingQueueRepository.findAll();
    }

    public Optional<ProcessingQueue> getQueueById(Long id) {
        return processingQueueRepository.findById(id);
    }

    public ProcessingQueue createQueue(ProcessingQueue queue) {
        return processingQueueRepository.save(queue);
    }

    public void deleteQueue(Long id) {
        processingQueueRepository.deleteById(id);
    }
}
