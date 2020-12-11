package by.nevar.theatre.forms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationRequestDto {

    @NotBlank(message = "Fill the username")
    @Length(max = 2048, message = "Too long")
    @Length(min = 3, message = "Too short")
    private String username;

    @NotBlank(message = "Fill the password")
    @Length(max = 100, message = "Too long")
    @Length(min = 5, message = "Too short")
    private String password;

    public AuthenticationRequestDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
