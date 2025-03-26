package cl.labs.conversion.controller;

import cl.labs.conversion.model.ConversionJob;
import cl.labs.conversion.service.ConversionJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conversion-jobs")
public class ConversionJobController {

    @Autowired
    private ConversionJobService conversionJobService;

    @GetMapping
    public List<ConversionJob> getAllJobs() {
        return conversionJobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConversionJob> getJobById(@PathVariable Long id) {
        Optional<ConversionJob> job = conversionJobService.getJobById(id);
        return job.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ConversionJob createJob(@RequestBody ConversionJob job) {
        return conversionJobService.createJob(job);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConversionJob> updateJob(@PathVariable Long id, @RequestBody ConversionJob updatedJob) {
        try {
            ConversionJob job = conversionJobService.updateJob(id, updatedJob);
            return ResponseEntity.ok(job);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        conversionJobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}
