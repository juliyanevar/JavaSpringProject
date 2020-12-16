package by.nevar.gamefinder.forms;

import by.nevar.gamefinder.models.User;
import lombok.Getter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Getter
public class RegistrationPlayerModel {

    @NotBlank(message = "Username cannot be empty")
    @Length(min = 8, max = 40, message = "Username length must be between 8 and 40 characters")
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Wrong email address")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Length(min = 8, max = 16, message = "Password length must be between 8 and 16 characters")
    private String password;

    public User ToUser() {
        return new User(
                this.getUsername(),
                this.getPassword()
        );
    }
}
