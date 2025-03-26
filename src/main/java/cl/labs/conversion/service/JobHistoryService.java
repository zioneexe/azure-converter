package cl.labs.conversion.service;

import cl.labs.conversion.model.JobHistory;
import cl.labs.conversion.repository.JobHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobHistoryService {

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    public List<JobHistory> getAllHistories() {
        return jobHistoryRepository.findAll();
    }

    public Optional<JobHistory> getHistoryById(Long id) {
        return jobHistoryRepository.findById(id);
    }

    public JobHistory createHistory(JobHistory history) {
        return jobHistoryRepository.save(history);
    }

    public void deleteHistory(Long id) {
        jobHistoryRepository.deleteById(id);
    }
}
