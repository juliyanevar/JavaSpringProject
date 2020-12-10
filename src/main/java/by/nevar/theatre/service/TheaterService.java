package by.nevar.theatre.service;

import by.nevar.theatre.forms.TheaterForm;
import by.nevar.theatre.models.Theater;
import by.nevar.theatre.repository.ITheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {
    @Autowired
    private ITheaterRepository theaterRepository;

    @Autowired
    public TheaterService(){
    }

    public Theater LoadTheaterByName(String name) {
        Theater theater = theaterRepository.findByName(name);

        if (theater == null) {
            //exception
        }

        return theater;
    }

    public Theater FromTheaterForm(TheaterForm form) {
        Theater theater = new Theater();
        if (form == null) {
            return theater;
        }
        if (form.getId() != null) {
            theater.setId(form.getId());
        }
        if (form.getName() != "" && form.getName() != null) {
            theater.setName(form.getName());
        }
        return theater;
    }

    public Theater AddNewTheater(Theater theater){
        Theater theaterFromDB=theaterRepository.findByName(theater.getName());
        if(theaterFromDB!=null){
            return null;
        }
        Theater newTheater = new Theater();
        newTheater.setName(theater.getName());
        theaterRepository.save(newTheater);
        return newTheater;
    }

    public Theater EditTheater(Theater theater){
        Theater theaterFromDB=theaterRepository.findById(theater.getId()).get();
        if(theaterFromDB==null){
            return null;
        }
        theaterRepository.save(theater);
        return theater;
    }

    public void DeleteTheater(Theater theater){
        Integer theaterId = theaterRepository.findById(theater.getId()).get().getId();
        if(theaterId!=null){
            theaterRepository.deleteById(theaterId);
        }
    }
}
