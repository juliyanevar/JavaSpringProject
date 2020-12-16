package by.nevar.gamefinder.service.serviceInterfaces;

import by.nevar.gamefinder.models.Championship;
import by.nevar.gamefinder.models.User;

public interface IMyRequestService {
    void addRequest(String status, String message, User user, Championship championship);
}

