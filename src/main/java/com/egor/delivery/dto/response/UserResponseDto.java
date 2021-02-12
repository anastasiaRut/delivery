package com.egor.delivery.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;

    private String name;

    private String surname;

    private String username;

    private String password;

    private Long roleId;
}
