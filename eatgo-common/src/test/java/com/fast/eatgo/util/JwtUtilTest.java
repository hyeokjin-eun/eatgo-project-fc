package com.fast.eatgo.util;

import org.junit.Test;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class JwtUtilTest {

    @Test
    public void createToken() {
        String secret = "secret-key-eatgo-fastcampus-project";
        JwtUtil jwtUtil = new JwtUtil(secret);

        String token = jwtUtil.createToken(1L, "Test1");

        assertThat(token, containsString("."));
    }
}