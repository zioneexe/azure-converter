package cl.labs.conversion.controller;

import cl.labs.conversion.model.JobStatus;
import cl.labs.conversion.service.JobStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/job-statuses")
public class JobStatusController {

    @Autowired
    private JobStatusService jobStatusService;

    @GetMapping
    public List<JobStatus> getAllStatuses() {
        return jobStatusService.getAllStatuses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobStatus> getStatusById(@PathVariable Long id) {
        Optional<JobStatus> status = jobStatusService.getStatusById(id);
        return status.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public JobStatus createStatus(@RequestBody JobStatus status) {
        return jobStatusService.createStatus(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Long id) {
        jobStatusService.deleteStatus(id);
        return ResponseEntity.noContent().build();
    }
}