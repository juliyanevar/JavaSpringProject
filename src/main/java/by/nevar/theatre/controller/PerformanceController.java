package by.nevar.theatre.controller;

import by.nevar.theatre.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class PerformanceController {
    private static List<Performance> performances = new ArrayList<Performance>();
    private static List<Theatre> theatres = new ArrayList<Theatre>();

    static {
        theatres.add(new Theatre("Большой театр"));
        theatres.add(new Theatre("Театр киноактера"));
        theatres.add(new Theatre("Театр музкомедии"));
        theatres.add(new Theatre("Купаловский театр"));
        theatres.add(new Theatre("Театр юного зрителя"));
        theatres.add(new Theatre("Театр кукол"));
        theatres.add(new Theatre("Молодежный театр"));
        theatres.add(new Theatre("Театр эстрады"));
        theatres.add(new Theatre("Новый драматический театр"));
        theatres.add(new Theatre("Камерный драматический театр"));
        theatres.add(new Theatre("Театр Горького"));
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping(value = {"/", "/index"})
    public ModelAndView index(Model model){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);
        log.info("/index was called");
        return  modelAndView;
    }

    @GetMapping(value = {"/alltheatres"})
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("theatrelist");
        model.addAttribute("theatres", theatres);
        log.info("/alltheatres was called");
        return modelAndView;
    }

}
