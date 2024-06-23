package miu.edu.ADS.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    @NotEmpty(message = "Email cannot be null or Empty")
    private String email;
    @NotEmpty(message = "Password cannot be null or Empty")
    private String password;
}
