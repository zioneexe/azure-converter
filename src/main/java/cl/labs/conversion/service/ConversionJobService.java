package cl.labs.conversion.service;

import cl.labs.conversion.model.ConversionJob;
import cl.labs.conversion.repository.ConversionJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversionJobService {

    @Autowired
    private ConversionJobRepository conversionJobRepository;

    public List<ConversionJob> getAllJobs() {
        return conversionJobRepository.findAll();
    }

    public Optional<ConversionJob> getJobById(Long id) {
        return conversionJobRepository.findById(id);
    }

    public ConversionJob createJob(ConversionJob job) {
        return conversionJobRepository.save(job);
    }

    public ConversionJob updateJob(Long id, ConversionJob updatedJob) {
        Optional<ConversionJob> existingJob = conversionJobRepository.findById(id);
        if (existingJob.isPresent()) {
            ConversionJob job = existingJob.get();
            //job.setInputFormat(updatedJob.getInputFormat());
            //job.setOutputFormat(updatedJob.getOutputFormat());
            //job.setStatus(updatedJob.getStatus());
            //job.setCompletedAt(updatedJob.getCompletedAt());
            return conversionJobRepository.save(job);
        } else {
            throw new RuntimeException("Job not found with ID: " + id);
        }
    }

    public void deleteJob(Long id) {
        conversionJobRepository.deleteById(id);
    }
}
