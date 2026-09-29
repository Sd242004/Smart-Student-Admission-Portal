package com.admission.student_admission.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.admission.student_admission.entity.Application;
import com.admission.student_admission.repository.ApplicationRepository;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final NotificationService notificationService;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            NotificationService notificationService) {

        this.applicationRepository = applicationRepository;
        this.notificationService = notificationService;
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

            status = status.trim();

            application.setStatus(status);

            Application savedApplication = applicationRepository.save(application);

            notificationService.createNotification(
                    savedApplication,
                    "Your application " + savedApplication.getApplicationNumber()
                            + " has been " + status
            );

            return savedApplication;
        }

        return null;
    }
}