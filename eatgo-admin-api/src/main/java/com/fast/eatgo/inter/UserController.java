package com.fast.eatgo.inter;

import ch.qos.logback.classic.spi.TurboFilterList;
import com.fast.eatgo.application.UserService;
import com.fast.eatgo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> list() {
        return userService.getUsers();
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {
        User user = userService.addUser(resource);

        URI uri = new URI("/user/" + user.getId());
        return ResponseEntity.created(uri).body("{}");
    }

    @PatchMapping("{id}")
    public User update(@PathVariable("id") Long id, @RequestBody User resource) {
        return userService.updateUser(id, resource);
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deactivateUser(id);
        return "{}";
    }
}

