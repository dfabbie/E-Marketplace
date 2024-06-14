package com.marketplace.demo.Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GlobalRecordVm {

    @Data
    public static class LoginVm {
        @NotBlank
        private String username;

        @NotBlank
        private String password;
    }

    @Data
    @AllArgsConstructor
    public static class AuthenticationVm {
        private String accessToken;
    }
}

