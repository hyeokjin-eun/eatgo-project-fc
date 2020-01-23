package com.fast.eatgo.util;

import io.jsonwebtoken.Claims;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class JwtUtilTest {

    private static final String SECRET = "secret-key-eatgo-fastcampus-project";

    private JwtUtil jwtUtil;

    @Before
    public void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    public void createToken() {
        String token = jwtUtil.createToken(1L, "Test1",null);

        assertThat(token, containsString("."));
    }

    @Test
    public void getClaims() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJUZXN0MSJ9.YKYvqkmiv5hWdGkREOqiELSWCzMQs7ABeQXbMTkyLMw";
        Claims claims = jwtUtil.getClaims(token);

        assertThat(claims.get("userId", Long.class),  is(1L));
        assertThat(claims.get("name"),  is("Test1"));
    }
}