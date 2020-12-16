package by.nevar.gamefinder.rest;

import by.nevar.gamefinder.dto.AuthenticationRequestDto;
import by.nevar.gamefinder.models.*;
import by.nevar.gamefinder.security.jwt.JwtTokenProvider;
import by.nevar.gamefinder.service.ChampionshipService;
import by.nevar.gamefinder.service.RequestService;
import by.nevar.gamefinder.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final RequestService requestService;
    private final ChampionshipService championshipService;
    private final PlayerValidator playerValidator;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, RequestService requestService, ChampionshipService championshipService, PlayerValidator playerValidator) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.requestService = requestService;
        this.championshipService = championshipService;
        this.playerValidator = playerValidator;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<User> Register(@RequestBody AuthenticationRequestDto requestDto) throws MethodArgumentNotValidException {

        try {
            String username = requestDto.getUsername();
            User user = userService.findByUsername(username);
            if (user != null) {
                throw new UsernameNotFoundException("User with username: " + username + " already exist");
            }

            user = new User(requestDto.getUsername(), requestDto.getPassword());

            userService.register(user);

            Map<Object, Object> response = new HashMap<>();

            log.info("Get request : /api/v1/auth/registration");
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (AuthenticationException e) {
            log.info("Get request : /api/v1/auth/registration ---- Invalid username");
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("token", token);
            log.info("Get request : /api/v1/auth/login");
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            log.info("Get request : /api/v1/auth/login ---- Invalid username or password");
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping(value = {"/userinfo"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetUsername(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUsername(token);
        User user = userService.findByUsername(username);
        Role role = user.getRoles().get(0);
        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("role", role.getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = {"/championships"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Championship>> championshipList() {
        return new ResponseEntity<>(championshipService.findAll()
                .stream()
                .filter(i -> i.getStatus() != Status.DELETED)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = {"/requests"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RequestDto>> requestsList() {
        var list = requestService.findAll()
                .stream()
                .filter(i -> i.getStatus().equals("Pending"))
                .collect(Collectors.toList());
        List<RequestDto> ret_list = new ArrayList<>();
        for (int i = 0; i < 10 && i < list.size(); i++) {
            ret_list.add(RequestDto.FromRequest(list.get(i)));
        }
        return new ResponseEntity<>(ret_list, HttpStatus.OK);
    }

    @PostMapping(value = {"/request"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestDto> getRequestsMessage(@RequestBody String string) {
        string = string.substring(1, string.length() - 1);
        String[] list = string.split(" => ");
        User user = userService.findByUsername(list[0]);
        Championship championship = championshipService.findByName(list[1]);
        Request request =
                requestService.findByUandC(user
                        ,
                        championship
                );
        var ret = RequestDto.FromRequest(request);
        return new ResponseEntity<RequestDto>(ret, HttpStatus.OK);
    }

}
