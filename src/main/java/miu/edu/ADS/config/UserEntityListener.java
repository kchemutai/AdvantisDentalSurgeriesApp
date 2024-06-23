package miu.edu.ADS.config;

import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import miu.edu.ADS.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityListener {

    private final PasswordEncoder passwordEncoder;

   @PrePersist
    public void encodePassword(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }
}

