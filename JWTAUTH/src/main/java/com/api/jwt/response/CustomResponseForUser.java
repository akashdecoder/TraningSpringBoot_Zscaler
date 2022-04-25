package com.api.jwt.response;

import com.api.jwt.model.AdminDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class CustomResponseForUser {

    private @Getter @Setter Date date;

    private @Getter @Setter String message;

    private @Getter @Setter String status;
}
