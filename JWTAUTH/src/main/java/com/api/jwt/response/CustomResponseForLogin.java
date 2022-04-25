package com.api.jwt.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class CustomResponseForLogin {
    private @Getter @Setter Date date;

    private @Getter @Setter String message;

    private @Getter @Setter String status;

    private @Getter @Setter String jwt;

    private @Getter @Setter String firstName;

    private @Getter @Setter String lastName;

    private @Getter @Setter String emailId;
}
