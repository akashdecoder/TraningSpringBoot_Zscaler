package com.api.jwt.security;

import com.api.jwt.model.AdminDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
public class AdminDetailsImpl implements UserDetails {

    private static final @Getter @Setter long serialVersionUID = 1L;

    static final @Getter @Setter Logger log = LoggerFactory.getLogger(AdminDetailsImpl.class);

    private @Getter @Setter long adminId;

    private @Getter @Setter String firstName;

    private @Getter @Setter String lastName;

    private @Getter @Setter String emailId;

    private @Getter @Setter String password;

    private @Getter @Setter Collection<? extends GrantedAuthority> authorities;

    public AdminDetailsImpl(long adminId, String firstName, String lastName, String emailId,
                            String password, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.adminId = adminId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.password = password;
        this.authorities = authorities;
    }



    public static Logger getLog() {
        return log;
    }

    public static AdminDetailsImpl build(AdminDetails admin) {
        log.info("****Inside AdminDetailsImpl build method***");

        //	List<AdminRoles> adminData=adminRolesRepository.findRoles();


        List<GrantedAuthority> authorities=new LinkedList<>();
        log.info(" After authoritiesList-------");



        admin.getAdminRole();
        log.info("Admin role in AdminDetailsImpl----"+admin.getAdminRole());
        if(admin.getAdminRole()!=null) {
            log.info("Inside if AdminRole is not null"+admin.getAdminRole());
            authorities.add(new SimpleGrantedAuthority(admin.getAdminRole()));
        }

		/*
		 if(ERole.ADMIN_ROLE==admin.getAdminRole()){
			log.info("Inside if Admin role is not null-"+admin.getAdminRole());
			authorities.add(new SimpleGrantedAuthority("ADMIN_ROLE"));
		}else if(ERole.MASTERADMIN_ROLE==admin.getAdminRole()) {
			log.info("Inside if UserRole is not null"+admin.getAdminRole());
			authorities.add(new SimpleGrantedAuthority("MASTERADMIN_ROLE"));
		}

		 */



        log.info("Authorities-----"+authorities);
        log.info(" Before build of AdminDetailsImpl");
        return new AdminDetailsImpl(admin.getAdminId(), admin.getFirstName(), admin.getLastName(),admin.getEmailId(),admin.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AdminDetailsImpl admin = (AdminDetailsImpl) o;
        return Objects.equals(adminId, admin.adminId);
    }

    @Override
    public String toString() {
        return "AdminDetailsImpl{" +
                "adminId=" + adminId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
