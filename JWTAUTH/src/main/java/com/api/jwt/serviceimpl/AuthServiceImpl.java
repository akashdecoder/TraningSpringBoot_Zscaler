package com.api.jwt.serviceimpl;

import com.api.jwt.model.AdminDetails;
import com.api.jwt.repository.AuthRepository;
import com.api.jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AdminDetails createAdmin(AdminDetails adminDetails) {
        adminDetails.setPassword(passwordEncoder.encode(adminDetails.getPassword()));
        return authRepository.save(adminDetails);
    }

    @Override
    public List<AdminDetails> getAdminDetails() {
        return authRepository.findAll();
    }

    @Override
    public Optional<AdminDetails> getAdminById(Long id) {
        return authRepository.findById(id);
    }

    @Override
    public AdminDetails fetchAdminByEmail(String emailId) {
        return authRepository.fetchAdminByEmail(emailId);
    }


}
