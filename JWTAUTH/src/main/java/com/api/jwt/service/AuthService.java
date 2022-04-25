package com.api.jwt.service;

import com.api.jwt.model.AdminDetails;

import java.util.List;
import java.util.Optional;

public interface AuthService {

    public AdminDetails createAdmin(AdminDetails adminDetails);

    public List<AdminDetails> getAdminDetails();

    public Optional<AdminDetails> getAdminById(Long id);

    public AdminDetails fetchAdminByEmail(String emailId);
}
