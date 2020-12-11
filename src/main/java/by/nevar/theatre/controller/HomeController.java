package by.nevar.theatre.controller;

import by.nevar.theatre.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class HomeController {


    @GetMapping("/")
    public ModelAndView home(
            @AuthenticationPrincipal User user,
            Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        log.info("/ was called");
        return modelAndView;
    }
}
