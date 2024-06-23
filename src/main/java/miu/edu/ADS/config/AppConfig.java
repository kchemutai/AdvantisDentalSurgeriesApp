package miu.edu.ADS.config;

import lombok.RequiredArgsConstructor;
import miu.edu.ADS.auth.dto.RegisterRequest;
import miu.edu.ADS.auth.dto.RegisterResponse;
import miu.edu.ADS.dto.appointment.AppointmentRequest;
import miu.edu.ADS.dto.appointment.AppointmentResponse;
import miu.edu.ADS.model.Appointment;
import miu.edu.ADS.model.User;
import miu.edu.ADS.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final UserRepository userRepository;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Mapping nested objects for AppointmentRequest to Appointment
        modelMapper.typeMap(AppointmentRequest.class, Appointment.class)
                .addMappings(mapper -> {
                    mapper.map(AppointmentRequest::getDate, Appointment::setDate);
                    mapper.map(AppointmentRequest::getTime, Appointment::setTime);
                    mapper.map(AppointmentRequest::getStatus, Appointment::setStatus);
                    // The lookup will be handled in the service
                });

        // Mapping nested objects for Appointment to AppointmentResponse
        modelMapper.typeMap(Appointment.class, AppointmentResponse.class)
                .addMappings(mapper -> {
                    mapper.map(Appointment::getDate, AppointmentResponse::setDate);
                    mapper.map(Appointment::getTime, AppointmentResponse::setTime);
                    mapper.map(Appointment::getStatus, AppointmentResponse::setStatus);
                    mapper.map(Appointment::getSurgery, AppointmentResponse::setSurgery);
                    mapper.map(Appointment::getPatient, AppointmentResponse::setPatient);
                    mapper.map(Appointment::getDentist, AppointmentResponse::setDentist);
                });

        // Mapping RegisterRequest to User
        modelMapper.typeMap(RegisterRequest.class, User.class)
                .addMappings(mapper -> {
                    mapper.map(RegisterRequest::getFirstName, User::setFirstName);
                    mapper.map(RegisterRequest::getLastName, User::setLastName);
                    mapper.map(RegisterRequest::getPhoneNumber, User::setPhoneNumber);
                    mapper.map(RegisterRequest::getEmail, User::setEmail);
                    mapper.map(RegisterRequest::getPassword, User::setPassword);
                    mapper.map(RegisterRequest::getRole, User::setRole);
                });

        // Mapping User to RegisterResponse
        modelMapper.typeMap(User.class, RegisterResponse.class)
                .addMappings(mapper -> {
                    mapper.map(User::getFirstName, RegisterResponse::setFirstName);
                    mapper.map(User::getLastName, RegisterResponse::setLastName);
                    mapper.map(User::getPhoneNumber, RegisterResponse::setPhoneNumber);
                    mapper.map(User::getEmail, RegisterResponse::setEmail);
                    mapper.map(User::getPassword, RegisterResponse::setPassword);
                    mapper.map(User::getRole, RegisterResponse::setRole);
                });

        return modelMapper;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService());
        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username).orElseThrow(
                ()->new UsernameNotFoundException("User with email "+username+" not found")
        );
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
