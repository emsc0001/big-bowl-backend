package bigbowl.security_demo.repository;

import bigbowl.security_demo.entity.newUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewUserRepository extends JpaRepository<newUser, String> {
}
