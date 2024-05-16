package bigbowl.security_demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserDtoResponse {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zipCode;

    public GetUserDtoResponse(String username, String email, String firstName, String lastName, String address, String city, String zipCode) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
    }

    // getters and setters...
}