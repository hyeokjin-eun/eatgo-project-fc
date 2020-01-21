package com.fast.eatgo.inter;

import com.fast.eatgo.application.UserService;
import com.fast.eatgo.domain.User;
import com.fast.eatgo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final UserService userService;

    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    public SessionController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("")
    public ResponseEntity<SessionResponseDto> create(@RequestBody SessionRequestDto resource) throws URISyntaxException {

        User user = userService.authenticate(resource.getEmail(), resource.getPassword());

        String accessToken = jwtUtil.createToken(user.getId(), user.getName());
        String uri = "/session";

        return ResponseEntity.created(new URI(uri))
                .body(SessionResponseDto.builder()
                        .accessToken(accessToken)
                        .build());
    }
}
