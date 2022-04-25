package com.api.jwt.repository;

import com.api.jwt.model.AdminDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AdminDetails, Long> {

    @Query("SELECT a FROM AdminDetails a WHERE a.emailId=?1")
    public AdminDetails fetchAdminByEmail(String emailId);
}
