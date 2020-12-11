package by.nevar.theatre.controller.rest;

import by.nevar.theatre.forms.UserForm;
import by.nevar.theatre.models.Performance;
import by.nevar.theatre.models.User;
import by.nevar.theatre.repository.IPerformanceRepository;
import by.nevar.theatre.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPerformanceRepository performanceRepository;

    @GetMapping("/performances")
    public ModelAndView getPerformanceList(
            Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Performance> performances = performanceRepository.findAll();

        model.addAttribute("performances", performances);

        modelAndView.setViewName("performances");
        log.info("/api/v1/users/performances was called");
        return modelAndView;
    }

//    @PostMapping("/games")
//    public ModelAndView addGame(
//            @RequestParam String name,
//            @RequestParam String version,
//            Model model) {
//        ModelAndView modelAndView = new ModelAndView();
//
//        Game newGame = new Game(name, version);
//
//        gameRepository.save(newGame);
//
//        Iterable<Game> games = gameRepository.findAll();
//
//        model.addAttribute("games", games);
//        modelAndView.setViewName("games");
//
//        return modelAndView;
//    }
//
//    @PostMapping("/filter")
//    public ModelAndView filter(
//            @RequestParam String filter,
//            Model model) {
//        ModelAndView modelAndView = new ModelAndView();
//
//        Iterable<Game> games = gameRepository.findByName(filter);
//
//        model.addAttribute("games", games);
//        modelAndView.setViewName("games");
//
//        return modelAndView;
//    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserForm> getUserById(@PathVariable(name = "id") Integer id) {
        User user = userRepository.findById(id).get();

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserForm result = UserForm.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

