package by.nevar.gamefinder.rest;

import by.nevar.gamefinder.dto.AdminUserDto;
import by.nevar.gamefinder.dto.PlayerDto;
import by.nevar.gamefinder.models.Role;
import by.nevar.gamefinder.models.User;
import by.nevar.gamefinder.security.jwt.JwtTokenProvider;
import by.nevar.gamefinder.service.ChampionshipService;
import by.nevar.gamefinder.service.RequestService;
import by.nevar.gamefinder.service.UserService;
import by.nevar.gamefinder.validator.PlayerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/userinfo")
public class UserInfoRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final RequestService requestService;
    private final ChampionshipService championshipService;
    private final PlayerValidator playerValidator;

    @Autowired
    public UserInfoRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, RequestService requestService, ChampionshipService championshipService, PlayerValidator playerValidator) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.requestService = requestService;
        this.championshipService = championshipService;
        this.playerValidator = playerValidator;
    }

    @GetMapping(value = {"/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetUsername(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUsername(token);
        User user = userService.findByUsername(username);
        Role role = user.getRoles().get(0);
        if(role.getName().equals("ROLE_PLAYER")) {
            PlayerDto playerDto = PlayerDto.fromUser(user);
            log.info("Get request : /api/v1/userinfo/ -- PLAYER");
            return new ResponseEntity<>(playerDto, HttpStatus.OK);
        }else{
            AdminUserDto adminUserDto = AdminUserDto.fromUser(user);
            log.info("Get request : /api/v1/userinfo/ -- ADMIN");
            return new ResponseEntity<>(adminUserDto, HttpStatus.OK);
        }
    }
}