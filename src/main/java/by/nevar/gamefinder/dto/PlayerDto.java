package by.nevar.gamefinder.dto;

import by.nevar.gamefinder.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerDto {
    private String username;
    private String email;
    private boolean nothing;

    public User toUser(){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        return user;
    }

    public static PlayerDto fromUser(User user) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setUsername(user.getUsername());
        playerDto.setEmail(user.getEmail());
        return playerDto;
    }
}
