package com.pianoschool.lms.repository;

import com.pianoschool.lms.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Boolean existsByEmail(String email);
}


