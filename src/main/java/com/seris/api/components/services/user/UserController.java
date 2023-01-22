package com.seris.api.components.services.user;

import com.seris.api.entities.user.User;
import com.seris.api.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public record UserController(
        UserService userService
) {
    @GetMapping("/username")
    public ResponseEntity<Response<User>> save(@RequestParam("username") String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }
}
