package com.api.jwt.response;

import com.api.jwt.model.AdminDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class AdminDetailsListResponse {

    private @Getter @Setter long count;
    private @Getter @Setter List<AdminDetails> adminDetailsList;

    @Override
    public String toString() {
        return "AdminDetailsListResponse{" +
                "count=" + count +
                ", adminDetailsList=" + adminDetailsList +
                '}';
    }
}
