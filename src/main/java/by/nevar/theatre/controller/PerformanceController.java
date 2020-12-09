package by.nevar.theatre.controller;

import by.nevar.theatre.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class PerformanceController {
    private static List<Performance> performances = new ArrayList<Performance>();
    private static List<Theater> theaters = new ArrayList<Theater>();

//    static {
//        theaters.add(new Theater("Большой театр"));
//        theaters.add(new Theater("Театр киноактера"));
//        theaters.add(new Theater("Театр музкомедии"));
//        theaters.add(new Theater("Купаловский театр"));
//        theaters.add(new Theater("Театр юного зрителя"));
//        theaters.add(new Theater("Театр кукол"));
//        theaters.add(new Theater("Молодежный театр"));
//        theaters.add(new Theater("Театр эстрады"));
//        theaters.add(new Theater("Новый драматический театр"));
//        theaters.add(new Theater("Камерный драматический театр"));
//        theaters.add(new Theater("Театр Горького"));
//    }

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
        model.addAttribute("theatres", theaters);
        log.info("/alltheatres was called");
        return modelAndView;
    }

}
