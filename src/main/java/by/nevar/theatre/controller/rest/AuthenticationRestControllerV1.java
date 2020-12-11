package by.nevar.theatre.controller.rest;

import by.nevar.theatre.forms.AuthenticationRequestDto;
import by.nevar.theatre.models.Role;
import by.nevar.theatre.models.User;
import by.nevar.theatre.repository.IUserRepository;
import by.nevar.theatre.security.JwtTokenProvider;
import by.nevar.theatre.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collector;


@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("login");
        AuthenticationRequestDto form = new AuthenticationRequestDto();
        model.addAttribute("form", form);
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView showRegistrationPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("registration");
        AuthenticationRequestDto form = new AuthenticationRequestDto();
        model.addAttribute("form", form);
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(
            @Valid @ModelAttribute("form") AuthenticationRequestDto form,
            BindingResult bindingResult,
            Model model) {
        try {
            if(bindingResult.hasErrors()){
                ModelAndView modelAndView = new ModelAndView("login");
                model.addAttribute("form", form);

                return modelAndView;
            }
            String username = form.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, form.getPassword()));
            User user = userRepository.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = "Bearer_"+jwtTokenProvider.createToken(username, new ArrayList<>(user.getRoles()));

            ModelAndView response = new ModelAndView();
            response.addObject("username", username);
            response.addObject("token", token);
            response.setViewName("home");

            response.addObject("isAdmin", user.getRoles().contains(Role.ADMIN));

            return response;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/registrate")
    public ModelAndView registrate(
            @Valid @ModelAttribute("form") AuthenticationRequestDto form,
            BindingResult bindingResult,
            Model model) {
        try {
            if(bindingResult.hasErrors()){
                ModelAndView modelAndView = new ModelAndView("registration");
                model.addAttribute("form", form);

                return modelAndView;
            }
            ModelAndView response = new ModelAndView();

            String userName = form.getUsername();
            String password = form.getPassword();


            if (userService.registrate(new User(userName, password, false, null)) == null) {
                response.addObject("errorMessage", "ERROR USER IS ALREADY EXIST");
                response.setViewName("registration");
                return response;
            }
            response.setViewName("login");
            return response;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
