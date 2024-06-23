package miu.edu.ADS.service.impl;

import lombok.RequiredArgsConstructor;
import miu.edu.ADS.exception.user.UserNotFoundException;
import miu.edu.ADS.model.User;
import miu.edu.ADS.repository.UserRepository;
import miu.edu.ADS.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> savedUser = userRepository.findByEmail(email);
        if(savedUser.isPresent()) {
            return Optional.of(savedUser.get());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> updateUser(Integer id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            User updatedUser = optionalUser.get();
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setPhoneNumber(user.getPhoneNumber());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setRole(user.getRole());
            return Optional.of(updatedUser);
        }
        throw new UserNotFoundException("Update Failed!, User with id " + id + " not found");
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Delete failed!, User with id " + id + " not found"));
        userRepository.deleteById(id);
    }
}
