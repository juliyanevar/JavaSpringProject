package by.nevar.gamefinder.dto;

import by.nevar.gamefinder.models.Championship;
import by.nevar.gamefinder.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChampionshipDto {
    private List<Championship> championships;

    public static Championship toSubject(ChampionshipDto championshipDto) {
        Championship championship = new Championship();
        championship.setName(championshipDto.championships.get(0).getName());
        return championship;
    }

    public static ChampionshipDto fromUser(User user) {
        ChampionshipDto championshipDto = new ChampionshipDto();
        return championshipDto;
    }

    public void setSubject(List<Championship> championship) {
        this.championships = championship;
    }
}
