package com.admission.student_admission.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admission.student_admission.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}