package bigbowl.security_demo.entity;

import bigbowl.security.entity.UserWithRoles;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class newUser extends UserWithRoles {
    private String username;
    private String password;

    public newUser(String username, String password) {
        super(username, password);
        this.username = username;
        this.password = password;
    }

}
