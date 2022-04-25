package com.api.jwt.response;

import com.api.jwt.model.AdminDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class SingleAdminResponse {

    private @Getter @Setter Date timeStamp;
    private @Getter @Setter String status;
    private @Getter @Setter AdminDetails adminDetails;
}
