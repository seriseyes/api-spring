package com.seris.api.components.services.user;

import com.seris.api.entities.user.User;
import com.seris.api.model.Response;
import com.seris.api.components.services.user.repository.UserRepository;
import com.seris.api.util.Validator;
import org.springframework.stereotype.Service;

@Service
public record UserService(
        Validator validator,
        UserRepository userRepository
) {
    public Response<User> findByUsername(String username) {
        return userRepository.findFirstByUsername(username).map(Response::success).orElse(Response.error(username + " нэртэй хэрэглэгч олдсонгүй"));
    }
}
