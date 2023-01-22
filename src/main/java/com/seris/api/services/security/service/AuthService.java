package com.seris.api.services.security.service;

import com.seris.api.entities.user.User;
import com.seris.api.enums.Role;
import com.seris.api.enums.Status;
import com.seris.api.model.Response;
import com.seris.api.model.Validation;
import com.seris.api.services.security.model.RegisterDto;
import com.seris.api.services.security.model.Token;
import com.seris.api.services.services.user.repository.UserRepository;
import com.seris.api.util.Validator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record AuthService(
        Validator validator,
        JwtService jwtService,
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager,
        UserRepository userRepository
) {

    public Response<User> register(User model) {
        Validation<User> validation = validator.validateEntity(model);
        if (validation.isError()) return validation.toResponse();

        if (userRepository.existsByUsername(model.getUsername())) {
            return Response.error(model.getUsername() + " давхардаж байна. Өөр нэвтрэх нэр оруулна уу");
        }

        if (model.getRoles() == null) model.setRoles(List.of(Role.USER));
        if (model.getStatus() == null) model.setStatus(Status.ACTIVE);
        if (model.getId() == null) {
            model.setPassword(passwordEncoder.encode(model.getPassword()));
        }
        return Response.success(userRepository.save(model));
    }

    public Response<Token> auth(RegisterDto model) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));
        var user = userRepository.findFirstByUsername(model.getUsername()).orElseThrow();
        return Response.success(jwtService.generateToken(user));
    }
}
