package com.api.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AdminDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter @Setter Long adminId;

    private @Getter @Setter String firstName;
    private @Getter @Setter String lastName;
    private @Getter @Setter String emailId;
    private @Getter @Setter String password;
    private @Getter @Setter String adminRole;
    private @Getter @Setter String department;
    private @Getter @Setter String adminAuthorities;

    @Override
    public String toString() {
        return "AdminDetails [adminId=" + adminId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", password=" + password + ", adminRole=" + adminRole + ", department=" + department + ", adminAuthorities=" + adminAuthorities + "]";

    }


}
