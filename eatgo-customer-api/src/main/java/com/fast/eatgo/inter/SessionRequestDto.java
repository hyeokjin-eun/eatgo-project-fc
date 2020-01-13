package com.fast.eatgo.inter;

import lombok.Builder;
import lombok.Data;

@Data
public class SessionRequestDto {

    private String email;

    private String password;
}
