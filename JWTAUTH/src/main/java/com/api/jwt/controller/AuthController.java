package com.api.jwt.controller;

import com.api.jwt.model.AdminDetails;
import com.api.jwt.response.*;
import com.api.jwt.security.AdminDetailsImpl;
import com.api.jwt.security.JwtUtils;
import com.api.jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @RequestMapping(value = "/createAdmin", method = RequestMethod.POST)
    public ResponseEntity<Object> createAdmin(@RequestBody AdminDetails adminDetails) {

        AdminDetails fetchAdminByEmail = authService.fetchAdminByEmail(adminDetails.getEmailId());
        if(fetchAdminByEmail == null) {
            AdminDetails admin = authService.createAdmin(adminDetails);
            AdminDetailsResponse adminDetailsResponse = new AdminDetailsResponse(new Date(), "Admin created successfully", "200", adminDetails);
            return new ResponseEntity<Object>(adminDetailsResponse, HttpStatus.CREATED);
        } else {
            CustomResponseForUser customResponseForUser = new CustomResponseForUser(new Date(), "Admin already exists", "409");
            return new ResponseEntity<Object>(customResponseForUser, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> getAdminDetails() {
        List<AdminDetails> adminDetailsList = authService.getAdminDetails();
        AdminDetailsListResponse adminDetailsListResponse = new AdminDetailsListResponse(adminDetailsList.size(), adminDetailsList);
        return new ResponseEntity<Object>(adminDetailsListResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> getAdminDetailsById(@RequestBody AdminDetails a) {
        Optional<AdminDetails> adminDetails = authService.getAdminById(a.getAdminId());
        SingleAdminResponse singleAdminResponse = new SingleAdminResponse(new Date(), "200", adminDetails.get());
        return new ResponseEntity<Object>(singleAdminResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> adminLogin(@RequestBody AdminDetails adminDetails) {
        AdminDetails admin = authService.fetchAdminByEmail(adminDetails.getEmailId());
        if(admin != null) {
            if(passwordEncoder.matches(adminDetails.getPassword(), admin.getPassword()) == true) {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDetails.getEmailId(), adminDetails.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication); //hold details of authentication
                String jwtToken = jwtUtils.generateJwtToken(authentication);
                AdminDetailsImpl details = (AdminDetailsImpl) authentication.getPrincipal();

                if(details != null) {
                    CustomResponseForLogin customResponseForLogin = new CustomResponseForLogin(new Date(), "Login Successfull", "200", jwtToken, admin.getFirstName(), admin.getLastName(), admin.getEmailId());
                    return new ResponseEntity<Object>(customResponseForLogin, HttpStatus.OK);
                } else {
                    CustomResponseForUser customResponseForUser = new CustomResponseForUser(new Date(), "Not Authenticated", "409");
                    return new ResponseEntity<Object>(customResponseForUser, HttpStatus.OK);
                }

            } else {
                CustomResponseForUser customResponseForUser = new CustomResponseForUser(new Date(), "Invalid Credentials", "409");
                return new ResponseEntity<Object>(customResponseForUser, HttpStatus.OK);
            }
        } else {
            CustomResponseForUser customResponseForUser = new CustomResponseForUser(new Date(), "Admin Not Found", "409");
            return new ResponseEntity<Object>(customResponseForUser, HttpStatus.OK);
        }
    }
}
