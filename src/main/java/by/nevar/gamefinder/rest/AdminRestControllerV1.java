package by.nevar.gamefinder.rest;

import by.nevar.gamefinder.dto.AdminUserDto;
import by.nevar.gamefinder.models.Championship;
import by.nevar.gamefinder.models.User;
import by.nevar.gamefinder.service.ChampionshipService;
import by.nevar.gamefinder.service.RequestService;
import by.nevar.gamefinder.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;
    private final ChampionshipService subjectService;
    private final RequestService requestService;

    @Autowired
    public AdminRestControllerV1(UserService userService, ChampionshipService subjectService, RequestService requestService) {
        this.userService = userService;
        this.subjectService = subjectService;
        this.requestService = requestService;
    }

    @GetMapping(value = "users/{username}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "username") String username) {
        User user;
        user = userService.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);
        log.info("Get request : /api/v1/admin/users");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("addChampionship")
    public ResponseEntity addChampionship(RequestEntity<Championship> championship) {
        subjectService.addChampionship(Objects.requireNonNull(championship.getBody()).getName());
        log.info("Get request : /api/v1/admin/addChampionship");
        return new ResponseEntity<>(Objects.requireNonNull(championship.getBody()).getName(), HttpStatus.CREATED);
    }

    @PostMapping(value = {"/acceptRequest"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity acceptRequest(@RequestBody String string) {
        requestService.setRequestStatus(string,true);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = {"/refuseRequest"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity refuseRequest(@RequestBody String string) {
        requestService.setRequestStatus(string,false);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("deActivateChampionship")
    public ResponseEntity deActivateChampionship(RequestEntity<Championship> championship) {
        subjectService.deleteChampionshipByName(Objects.requireNonNull(championship.getBody()).getName());
        log.info("Get request : /api/v1/admin/deActivateChampionship");
        return new ResponseEntity<>(Objects.requireNonNull(championship.getBody()).getName(), HttpStatus.CREATED);
    }
}