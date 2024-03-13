package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User registerUser(UserRegistrationDTO registrationDTO) {
        // You can add validation and additional logic here if needed
        User newUser = new User(
                registrationDTO.getUsername(),
                registrationDTO.getPassword(),
                registrationDTO.getRole(),
                registrationDTO.getEmail()  // Add this line to pass the email
        );
        return userRepository.save(newUser);
    }

    // Other methods...
}

