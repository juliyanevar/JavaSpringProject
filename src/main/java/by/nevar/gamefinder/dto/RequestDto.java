package by.nevar.gamefinder.dto;

import by.nevar.gamefinder.models.Request;
import lombok.Data;

@Data
public class RequestDto {
    private String status;
    private String message;
    private String user;
    private String championship;

    public RequestDto(String status, String message, String user, String championship) {
        this.status = status;
        this.message = message;
        this.user = user;
        this.championship = championship;
    }

    public RequestDto(){

    }

    public static RequestDto FromRequest(Request request) {
        RequestDto req = new RequestDto();
        req.status = request.getStatus();
        req.message = request.getMessage();
        req.user = request.getUser().getUsername();
        req.championship = request.getChampionship().getName();
        return req;
    }
}
