package cl.labs.conversion.service;

import cl.labs.conversion.model.JobFile;
import cl.labs.conversion.repository.JobFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobFileService {

    @Autowired
    private JobFileRepository jobFileRepository;

    public List<JobFile> getAllFiles() {
        return jobFileRepository.findAll();
    }

    public Optional<JobFile> getFileById(Long id) {
        return jobFileRepository.findById(id);
    }

    public JobFile createFile(JobFile file) {
        return jobFileRepository.save(file);
    }

    public void deleteFile(Long id) {
        jobFileRepository.deleteById(id);
    }
}
