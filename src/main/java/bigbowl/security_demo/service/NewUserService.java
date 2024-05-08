package bigbowl.security_demo.service;

import bigbowl.security_demo.dto.NewUserDtoRequest;
import bigbowl.security_demo.dto.NewUserDtoResponse;
import bigbowl.security_demo.entity.newUser;
import bigbowl.security_demo.repository.NewUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NewUserService {

    private final NewUserRepository newUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public NewUserService(NewUserRepository newUserRepository, PasswordEncoder passwordEncoder) {
        this.newUserRepository = newUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public NewUserDtoResponse createUser(NewUserDtoRequest newUserDtoRequest) {
        // Krypter adgangskoden, før du gemmer brugeren
        String encodedPassword = passwordEncoder.encode(newUserDtoRequest.getPassword());

        // Opret en ny brugerentitet baseret på DTO-anmodningen
        newUser newUserEntity = new newUser(newUserDtoRequest.getUsername(), encodedPassword);

        // Gem den nye bruger i databasen
        newUser savedUser = newUserRepository.save(newUserEntity);

        // Opret et svar DTO med brugerens brugernavn
        NewUserDtoResponse response = new NewUserDtoResponse(savedUser.getUsername());

        return response;
    }
}
