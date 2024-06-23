package miu.edu.ADS.auth.adapter;

import lombok.RequiredArgsConstructor;
import miu.edu.ADS.auth.dto.AuthenticationRequest;
import miu.edu.ADS.auth.dto.AuthenticationResponse;
import miu.edu.ADS.auth.dto.RegisterRequest;
import miu.edu.ADS.auth.dto.RegisterResponse;
import miu.edu.ADS.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationAdapter {
    private final ModelMapper modelMapper;

    public User convertAuthRequestToUser(AuthenticationRequest authenticationRequest) {
        return modelMapper.map(authenticationRequest, User.class);
    }

    public User convertRegisterRequestToUser(RegisterRequest registerRequest) {
        return modelMapper.map(registerRequest, User.class);
    }

    public AuthenticationResponse convertUserToAuthResponse(User user) {
        return modelMapper.map(user, AuthenticationResponse.class);
    }

    public RegisterResponse convertUserToRegisterResponse(User user) {
        return modelMapper.map(user, RegisterResponse.class);
    }
}
