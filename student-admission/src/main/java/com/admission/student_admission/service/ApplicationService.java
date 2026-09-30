package com.admission.student_admission.service;

import java.time.LocalDateTime;
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

    // Create application
    public Application createApplication(Application application) {

        if (application.getStatus() == null
                || application.getStatus().isBlank()) {

            application.setStatus("PENDING");
        }

        if (application.getAppliedAt() == null) {
            application.setAppliedAt(LocalDateTime.now());
        }

        if (application.getApplicationNumber() == null
                || application.getApplicationNumber().isBlank()) {

            long count = applicationRepository.count() + 1;

            application.setApplicationNumber(
                    String.format("APP%03d", count)
            );
        }

        return applicationRepository.save(application);
    }

    // Get all applications
    public List<Application> getAllApplications() {

        return applicationRepository.findAll();
    }

    // Get application by ID
    public Application getApplicationById(Long id) {

        return applicationRepository.findById(id).orElse(null);
    }

    // Update application
    public Application updateApplication(Application application) {

        return applicationRepository.save(application);
    }

    // Update application status
    public Application updateApplicationStatus(
            Long id,
            String status) {

        Application application =
                applicationRepository.findById(id).orElse(null);

        if (application == null) {
            return null;
        }

        application.setStatus(status);

        Application updatedApplication =
                applicationRepository.save(application);

        // Create notification for student
        if (application.getStudent() != null) {

            String message =
                    "Your application "
                    + application.getApplicationNumber()
                    + " has been "
                    + status;

            notificationService.createNotification(
                    application,
                    message
            );
        }

        return updatedApplication;
    }

    // Delete application
    public void deleteApplication(Long id) {

        applicationRepository.deleteById(id);
    }
}