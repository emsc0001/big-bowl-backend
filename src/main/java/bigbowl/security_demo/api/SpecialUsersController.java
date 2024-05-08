package bigbowl.security_demo.api;

import bigbowl.security.entity.Role;
import bigbowl.security.repository.RoleRepository;
import bigbowl.security_demo.entity.SpecialUser;
import bigbowl.security_demo.repository.SpecialUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class SpecialUsersController {

    private final SpecialUserRepository specialUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

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
            // Encrypt the password before saving the user
            String encodedPassword = passwordEncoder.encode(specialUser.getPassword());

            // Set the encoded password back to the specialUser
            specialUser.setPassword(encodedPassword);

            // Find or create role "USER"
            Role userRole = roleRepository.findById("USER").orElseGet(() -> roleRepository.save(new Role("USER")));

            // Add role to specialUser
            specialUser.addRole(userRole);

            // Save the user with the encrypted password and role
            specialUserRepository.save(specialUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Special user created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create special user: " + e.getMessage());
        }
    }
}