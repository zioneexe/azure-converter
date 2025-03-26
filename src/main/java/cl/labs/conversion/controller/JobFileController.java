package cl.labs.conversion.controller;

import cl.labs.conversion.model.JobFile;
import cl.labs.conversion.service.JobFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/job-files")
public class JobFileController {

    @Autowired
    private JobFileService jobFileService;

    @GetMapping
    public List<JobFile> getAllFiles() {
        return jobFileService.getAllFiles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobFile> getFileById(@PathVariable Long id) {
        Optional<JobFile> file = jobFileService.getFileById(id);
        return file.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public JobFile createFile(@RequestBody JobFile file) {
        return jobFileService.createFile(file);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
        jobFileService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}
