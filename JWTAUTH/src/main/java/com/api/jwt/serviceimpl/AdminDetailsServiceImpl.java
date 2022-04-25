package com.api.jwt.serviceimpl;

import com.api.jwt.model.AdminDetails;
import com.api.jwt.repository.AuthRepository;
import com.api.jwt.security.AdminDetailsImpl;
import com.api.jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        AdminDetails adminDetails = authRepository.fetchAdminByEmail(emailId);
        if(adminDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return AdminDetailsImpl.build(adminDetails);
    }
}
