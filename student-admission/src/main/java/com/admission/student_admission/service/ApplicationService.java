package com.admission.student_admission.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.admission.student_admission.entity.Application;
import com.admission.student_admission.repository.ApplicationRepository;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Application submitApplication(Application application) {
        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    public Application updateApplicationStatus(Long id, String status) {
        Application application = applicationRepository.findById(id).orElse(null);

        if (application != null) {
            application.setStatus(status);
            return applicationRepository.save(application);
        }

        return null;
    }
}