package cl.labs.conversion.controller;

import cl.labs.conversion.model.JobHistory;
import cl.labs.conversion.service.JobHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/job-histories")
public class JobHistoryController {

    @Autowired
    private JobHistoryService jobHistoryService;

    @GetMapping
    public List<JobHistory> getAllHistories() {
        return jobHistoryService.getAllHistories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobHistory> getHistoryById(@PathVariable Long id) {
        Optional<JobHistory> history = jobHistoryService.getHistoryById(id);
        return history.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public JobHistory createHistory(@RequestBody JobHistory history) {
        return jobHistoryService.createHistory(history);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistory(@PathVariable Long id) {
        jobHistoryService.deleteHistory(id);
        return ResponseEntity.noContent().build();
    }
}
