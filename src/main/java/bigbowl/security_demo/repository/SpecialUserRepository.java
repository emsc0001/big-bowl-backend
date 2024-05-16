package bigbowl.security_demo.repository;

import bigbowl.security_demo.entity.SpecialUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialUserRepository extends JpaRepository<SpecialUser, String> {
    SpecialUser findByUsernameIgnoreCase(String username);
}

