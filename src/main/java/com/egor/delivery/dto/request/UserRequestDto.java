package com.egor.delivery.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private Long id;

    private String name;

    private String password;

    private Long roleId;
}
