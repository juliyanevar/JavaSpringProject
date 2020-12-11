package by.nevar.theatre.forms;

import by.nevar.theatre.models.Role;
import by.nevar.theatre.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private Integer id;
    private String username;
    private String password;
    private Set<Role> roles;
    private boolean active;

    public static UserForm fromUser(User user) {
        UserForm userForm = new UserForm();
        userForm.setId(user.getId());
        userForm.setUsername(user.getUsername());

        return userForm;
    }
}
