package miu.edu.ADS.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.ADS.model.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private Role role;
}
