package cl.labs.conversion.service;

import cl.labs.conversion.model.JobStatus;
import cl.labs.conversion.repository.JobStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobStatusService {

    @Autowired
    private JobStatusRepository jobStatusRepository;

    public List<JobStatus> getAllStatuses() {
        return jobStatusRepository.findAll();
    }

    public Optional<JobStatus> getStatusById(Long id) {
        return jobStatusRepository.findById(id);
    }

    public JobStatus createStatus(JobStatus status) {
        return jobStatusRepository.save(status);
    }

    public void deleteStatus(Long id) {
        jobStatusRepository.deleteById(id);
    }
}
