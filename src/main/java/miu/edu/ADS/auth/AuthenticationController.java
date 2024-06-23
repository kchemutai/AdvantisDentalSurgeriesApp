package miu.edu.ADS.auth;

import lombok.RequiredArgsConstructor;
import miu.edu.ADS.auth.adapter.AuthenticationAdapter;
import miu.edu.ADS.auth.dto.AuthenticationRequest;
import miu.edu.ADS.auth.dto.AuthenticationResponse;
import miu.edu.ADS.auth.dto.RegisterRequest;
import miu.edu.ADS.auth.dto.RegisterResponse;
import miu.edu.ADS.model.User;
import miu.edu.ADS.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final AuthenticationAdapter authenticationAdapter;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        Optional<User> optionalUser = userService.save(authenticationAdapter.convertRegisterRequestToUser(registerRequest));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return ResponseEntity.ok(authenticationAdapter.convertUserToRegisterResponse(user));
        }
        return ResponseEntity.badRequest().build();
    }
}
