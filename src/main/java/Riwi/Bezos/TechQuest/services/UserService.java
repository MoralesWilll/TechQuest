package Riwi.Bezos.TechQuest.services;

import Riwi.Bezos.TechQuest.entities.User;
import Riwi.Bezos.TechQuest.repositories.UserRepository;
import Riwi.Bezos.TechQuest.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String authenticate(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {

                return jwtUtil.generateToken(user);
            }
        }
        throw new RuntimeException("Invalid credentials");
    }
}
