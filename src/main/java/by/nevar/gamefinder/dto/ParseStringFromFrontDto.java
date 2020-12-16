package by.nevar.gamefinder.dto;

import by.nevar.gamefinder.models.Request;
import by.nevar.gamefinder.service.ChampionshipService;
import by.nevar.gamefinder.service.RequestService;
import by.nevar.gamefinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class ParseStringFromFrontDto {
    private String string;

    @Autowired
    private RequestService requestService;
    @Autowired
    private UserService userService;
    @Autowired
    private ChampionshipService championshipService;

    ParseStringFromFrontDto(){
    }

    public ParseStringFromFrontDto(String string) {
        this.string = string;
    }

    public RequestDto GetRequestFromString(){
        string = string.substring(1,string.length()-1);
        String[] list = string.split("=>");
        Request request =
                requestService.findByUandC(
                        userService.findByUsername(list[0]),
                        championshipService.findByName(list[1])
                );
        return RequestDto.FromRequest(request);
    }
}

