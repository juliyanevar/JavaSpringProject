package by.nevar.theatre.controller.rest;

import by.nevar.theatre.forms.ActorForm;
import by.nevar.theatre.forms.TheaterForm;
import by.nevar.theatre.models.Actor;
import by.nevar.theatre.models.Theater;
import by.nevar.theatre.repository.IActorRepository;
import by.nevar.theatre.repository.ITheaterRepository;
import by.nevar.theatre.service.ActorService;
import by.nevar.theatre.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/admin/")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminRestControllerV1 {
//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private IActorRepository actorRepository;

    @Autowired
    private ActorService actorService;

    @Autowired
    private ITheaterRepository theaterRepository;

    @Autowired
    private TheaterService theaterService;
//
//    @Autowired
//    private RatingRepository ratingRepository;
//
//    @Autowired
//    private RatingService ratingService;

    @Autowired
    public AdminRestControllerV1() {
    }

//    @GetMapping(value = "{id}")
//    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Integer id) {
//        User user = userRepository.findById(id).get();
//
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        AdminUserDto result = AdminUserDto.fromUser(user);
//
//        return new ResponseEntity(result, HttpStatus.OK);
//    }

    @GetMapping("/home")
    public ModelAndView getAdminPage(Model model) {
        model.addAttribute("token", model.getAttribute("token"));

        Iterable<Actor> actors = actorRepository.findAll();
        model.addAttribute("actors", actors);

        Iterable<Theater> theaters = theaterRepository.findAll();
        model.addAttribute("championships", theaters);

        return toAdminPage(model);
    }

    @PostMapping("/AddActor")
    public ModelAndView addActor(
            @ModelAttribute("ActorForm") ActorForm form,
            BindingResult bindingResult,
            Model model) {
        actorService.AddNewActor(actorService.FromActorForm(form));

        Iterable<Actor> actors = actorRepository.findAll();
        model.addAttribute("actors", actors);

        return toAdminPage(model);
    }

    @PostMapping("/ChangeActor")
    public ModelAndView changeGActor(
            @ModelAttribute("ActorForm") ActorForm form,
            BindingResult bindingResult,
            Model model) {
        actorService.EditActor(actorService.FromActorForm(form));

        Iterable<Actor> actors = actorRepository.findAll();
        model.addAttribute("actors", actors);

        return toAdminPage(model);
    }

    @PostMapping("/DeleteActor")
    public ModelAndView deleteActor(
            @ModelAttribute("ActorForm") ActorForm form,
            BindingResult bindingResult,
            Model model) {
        actorService.DeleteActor(actorService.FromActorForm(form));

        Iterable<Actor> actors = actorRepository.findAll();
        model.addAttribute("actors", actors);

        return toAdminPage(model);
    }

    @PostMapping("/ActorFilter")
    public ModelAndView ActorFilter(
            @RequestParam String filter,
            Model model) {
        Iterable<Actor> actors = null;
        if(filter!="") {
            actors = actorRepository.findAllByName(filter);
        }
        else{
            actors = actorRepository.findAll();
        }
        model.addAttribute("actors", actors);
        return toAdminPage(model);
    }
    //------------------------
    @PostMapping("/AddCTheater")
    public ModelAndView addTheater(
            @ModelAttribute("TheaterForm") TheaterForm form,
            BindingResult bindingResult,
            Model model) {
        theaterService.AddNewTheater(theaterService.FromTheaterForm(form));

        Iterable<Theater> theaters = theaterRepository.findAll();
        model.addAttribute("theaters", theaters);

        return toAdminPage(model);
    }

    @PostMapping("/ChangeTheater")
    public ModelAndView changeTheater(
            @ModelAttribute("TheaterForm") TheaterForm form,
            BindingResult bindingResult,
            Model model) {
        theaterService.EditTheater(theaterService.FromTheaterForm(form));

        Iterable<Theater> theaters = theaterRepository.findAll();
        model.addAttribute("theaters", theaters);

        return toAdminPage(model);
    }

    @PostMapping("/DeleteTheater")
    public ModelAndView deleteTheater(
            @ModelAttribute("TheaterForm") TheaterForm form,
            BindingResult bindingResult,
            Model model) {
        theaterService.DeleteTheater(theaterService.FromTheaterForm(form));

        Iterable<Theater> theaters = theaterRepository.findAll();
        model.addAttribute("theaters", theaters);

        return toAdminPage(model);
    }

    @PostMapping("/TheaterFilter")
    public ModelAndView TheaterFilter(
            @RequestParam String filter,
            Model model) {
        Iterable<Theater> theaters = null;
        if(filter!="") {
            theaters = theaterRepository.findAllByName(filter);
        }
        else{
            theaters = theaterRepository.findAll();
        }
        model.addAttribute("theaters", theaters);
        return toAdminPage(model);
    }
    //------------------------
//    @PostMapping("/AddRating")
//    public ModelAndView addRating(
//            @ModelAttribute("RatingForm") RatingDto form,
//            BindingResult bindingResult,
//            Model model) {
//        ratingService.AddNewRating(ratingService.FromDto(form));
//
//        Iterable<Rating> ratings = ratingRepository.findAll();
//        model.addAttribute("ratings", ratings);
//
//        return toAdminPage(model);
//    }
//
//    @PostMapping("/ChangeRating")
//    public ModelAndView changeRating(
//            @ModelAttribute("RatingForm") RatingDto form,
//            BindingResult bindingResult,
//            Model model) {
//        ratingService.EditRating(ratingService.FromDto(form));
//
//        Iterable<Rating> ratings = ratingRepository.findAll();
//        model.addAttribute("ratings", ratings);
//
//        return toAdminPage(model);
//    }
//
//    @PostMapping("/DeleteRating")
//    public ModelAndView deleteRating(
//            @ModelAttribute("RatingForm") RatingDto form,
//            BindingResult bindingResult,
//            Model model) {
//        ratingService.DeleteRating(ratingService.FromDto(form));
//
//        Iterable<Rating> ratings = ratingRepository.findAll();
//        model.addAttribute("ratings", ratings);
//
//        return toAdminPage(model);
//    }
//
//    @PostMapping("/RatingFilter")
//    public ModelAndView RatingFilter(
//            @RequestParam String filter,
//            Model model) {
//        Iterable<Rating> ratings = null;
//        if(filter!="") {
//            //do filteres
//            ratings = ratingRepository.findAll();
//        }
//        else{
//            ratings = ratingRepository.findAll();
//        }
//        model.addAttribute("ratings", ratings);
//        return toAdminPage(model);
//    }


    public ModelAndView toAdminPage(Model model){
        TheaterForm theaterForm = new TheaterForm();
        model.addAttribute("TheaterForm", theaterForm);

//        RatingDto ratingForm = new RatingDto();
//        model.addAttribute("RatingForm", ratingForm);

        ActorForm actorForm = new ActorForm();
        model.addAttribute("ActorForm", actorForm);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminpage");

        return modelAndView;
    }
}
