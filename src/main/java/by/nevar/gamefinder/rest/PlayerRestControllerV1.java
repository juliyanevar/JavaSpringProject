package by.nevar.gamefinder.rest;

import by.nevar.gamefinder.dto.PlayerDto;
import by.nevar.gamefinder.models.Championship;
import by.nevar.gamefinder.models.User;
import by.nevar.gamefinder.service.ChampionshipService;
import by.nevar.gamefinder.service.RequestService;
import by.nevar.gamefinder.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/players/")
public class PlayerRestControllerV1 {
    private final UserService userService;
    private final ChampionshipService championshipService;
    private final RequestService requestService;

    @Autowired
    public PlayerRestControllerV1(UserService userService, ChampionshipService championshipService, RequestService requestService) {
        this.userService = userService;
        this.championshipService = championshipService;
        this.requestService = requestService;
    }

    @GetMapping(value = "{username}")
    public ResponseEntity<PlayerDto> getPlayerByUsername(@PathVariable(name = "username") String username) {
        User user = userService.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        PlayerDto result = PlayerDto.fromUser(user);
        log.info("Get request : /api/v1/auth/username");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "makeRequest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity makeRequests(@RequestBody String string) {
        log.info("Player make request");
        string = string.substring(1, string.length() - 1);
        String[] list = string.split("]");
        User user = userService.findByUsername(list[1]);
        Championship championship = championshipService.findByName(list[0]);
        if (!requestService.isExist(user, championship)) {
            requestService.addRequest(
                    "Pending",
                    list[2],
                    user,
                    championship
            );
            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "changeEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changeEmail(@RequestBody String string) {
        log.info("Player change email");
        string = string.substring(1, string.length() - 1);
        String[] list = string.split("]");
        User user = userService.findByUsername(list[0]);

        user.setEmail(list[1]);

        userService.save(user);

        return new ResponseEntity(HttpStatus.CONFLICT);
    }
}

