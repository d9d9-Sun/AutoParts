package by.mad.autoparts.services;

import by.mad.autoparts.models.Admin;
import by.mad.autoparts.models.UserRole;
import by.mad.autoparts.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(Admin user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return true;
    }

    public Admin getAdminByPrincipal(Principal principal) {
        if (principal != null) {
            return userRepository.findByUsername(principal.getName());
        }
        return new Admin();
    }

    public UserRole getAdminUserRole(Principal principal) {
        return userRepository.findByUsername(principal.getName())
                .getUserRoles()
                .stream()
                .findFirst()
                .orElseThrow();
    }

}
