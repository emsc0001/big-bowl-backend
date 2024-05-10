package bigbowl.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OpretResponse {
    private String username;
    private String password;

    public OpretResponse(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
