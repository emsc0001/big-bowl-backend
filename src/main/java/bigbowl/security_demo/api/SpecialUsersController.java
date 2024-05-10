package bigbowl.security_demo.api;

import bigbowl.security.entity.Role;
import bigbowl.security.repository.RoleRepository;
import bigbowl.security_demo.entity.SpecialUser;
import bigbowl.security_demo.repository.SpecialUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class SpecialUsersController {

    private final SpecialUserRepository specialUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private static final Logger logger = LoggerFactory.getLogger(SpecialUsersController.class);


    public SpecialUsersController(SpecialUserRepository specialUserRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.specialUserRepository = specialUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @GetMapping("/specialusers")
    public ResponseEntity<List<SpecialUser>> getSpecialUsers() {
        return ResponseEntity.ok(specialUserRepository.findAll());
    }

    @PostMapping("/specialusers")
    public ResponseEntity<String> createSpecialUser(@RequestBody SpecialUser specialUser) {
        try {
            // Ensure password is encoded
            if (specialUser.getPassword() != null) {
                logger.info("Original password: " + specialUser.getPassword());
                String encodedPassword = passwordEncoder.encode(specialUser.getPassword());
                logger.info("Encoded password: " + encodedPassword);
                specialUser.setPassword(encodedPassword);
            }

            // Add role "USER" if it doesn't exist
            Role userRole = roleRepository.findById("USER").orElseGet(() -> roleRepository.save(new Role("USER")));
            specialUser.addRole(userRole);

            // Save user with encoded password and role
            specialUserRepository.save(specialUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Special user created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create special user: " + e.getMessage());
        }
    }
}