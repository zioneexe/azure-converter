package cl.labs.conversion.controller;

import cl.labs.conversion.model.ProcessingQueue;
import cl.labs.conversion.service.ProcessingQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/processing-queues")
public class ProcessingQueueController {

    @Autowired
    private ProcessingQueueService processingQueueService;

    @GetMapping
    public List<ProcessingQueue> getAllQueues() {
        return processingQueueService.getAllQueues();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessingQueue> getQueueById(@PathVariable Long id) {
        Optional<ProcessingQueue> queue = processingQueueService.getQueueById(id);
        return queue.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProcessingQueue createQueue(@RequestBody ProcessingQueue queue) {
        return processingQueueService.createQueue(queue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQueue(@PathVariable Long id) {
        processingQueueService.deleteQueue(id);
        return ResponseEntity.noContent().build();
    }
}
