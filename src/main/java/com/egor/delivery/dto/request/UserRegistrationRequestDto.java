package com.egor.delivery.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequestDto {
    private String username;

    private String password;

    private String role;

    private String name;

    private String surname;

}
