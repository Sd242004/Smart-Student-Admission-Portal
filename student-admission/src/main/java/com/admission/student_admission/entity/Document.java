package com.admission.student_admission.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "application_id")
    private Application application;

    private String tenthMarksheet;
    private String twelfthMarksheet;
    private String photo;
    private String signature;

    public Document() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getTenthMarksheet() {
        return tenthMarksheet;
    }

    public void setTenthMarksheet(String tenthMarksheet) {
        this.tenthMarksheet = tenthMarksheet;
    }

    public String getTwelfthMarksheet() {
        return twelfthMarksheet;
    }

    public void setTwelfthMarksheet(String twelfthMarksheet) {
        this.twelfthMarksheet = twelfthMarksheet;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}