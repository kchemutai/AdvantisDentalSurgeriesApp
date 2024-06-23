package miu.edu.ADS.service;

import miu.edu.ADS.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> updateUser(Integer id, User user);
    Optional<User> findById(Integer id);
    void deleteById(Integer id);
}
