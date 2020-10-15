package by.nevar.theatre.controller;

import by.nevar.theatre.forms.UserForm;
import by.nevar.theatre.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Slf4j
@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public ModelAndView showRegistrationPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("registration");
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return modelAndView;
    }
    @PostMapping("/registration")
    public ModelAndView AddNewUser(Model model,
                                   @ModelAttribute("userForm") UserForm userForm) {
        ModelAndView modelAndView = new ModelAndView();

        String userName = userForm.getUsername();
        String password = userForm.getPassword();

        return modelAndView;
    }
}

