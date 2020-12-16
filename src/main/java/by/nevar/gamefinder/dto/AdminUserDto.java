package by.nevar.gamefinder.dto;

import by.nevar.gamefinder.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {
    private Long id;
    private String username;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        return user;
    }

    public static AdminUserDto fromUser(User user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setUsername(user.getUsername());
        return adminUserDto;
    }
}
