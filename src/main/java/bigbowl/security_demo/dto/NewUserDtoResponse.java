package bigbowl.security_demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewUserDtoResponse {
    private String username;
    private String password;

    public NewUserDtoResponse(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public NewUserDtoResponse(String username) {
    }
}