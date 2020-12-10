package by.nevar.theatre.forms;

import by.nevar.theatre.models.Role;
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
}
