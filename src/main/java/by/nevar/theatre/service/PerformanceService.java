package by.nevar.theatre.service;


import by.nevar.theatre.forms.PerformanceForm;
import by.nevar.theatre.models.Actor;
import by.nevar.theatre.models.Performance;
import by.nevar.theatre.models.Theater;
import by.nevar.theatre.repository.IPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
public class PerformanceService {
    @Autowired
    private IPerformanceRepository performanceRepository;

    @Autowired
    public PerformanceService() {
    }

    public Performance loadPerformanceByTitle(String title) {
        Performance performance = performanceRepository.findByTitle(title);

        if (performance == null) {
            //exception
        }


        return performance;
    }

    public List<Performance> loadPerformanceByGenre(String genre) {
        List<Performance> performances = performanceRepository.findByGenre(genre);

        if (performances == null) {
            //exception
        }

        return performances;
    }

    public List<Performance> loadPerformanceByTheater(Theater theater) {
        List<Performance> performances = performanceRepository.findByTheater(theater);

        if (performances == null) {
            //exception
        }

        return performances;
    }

    public List<Performance> loadPerformanceByDate(Date date) {
        List<Performance> performances = performanceRepository.findByDate(date);

        if (performances == null) {
            //exception
        }

        return performances;
    }

    public List<Performance> loadPerformanceByTime(Time time) {
        List<Performance> performances = performanceRepository.findByTime(time);

        if (performances == null) {
            //exception
        }

        return performances;
    }


    public Performance FromPerformanceForm(PerformanceForm form) {
        Performance performance = new Performance();
        if (form == null) {
            return performance;
        }
        if (form.getId() != null) {
            performance.setId(form.getId());
        }
        if (form.getTitle() != "" && form.getTitle() != null) {
            performance.setTitle(form.getTitle());
        }
        if (form.getGenre() != "" && form.getGenre() != null) {
            performance.setGenre(form.getGenre());
        }
        if (form.getTheater() != null) {
            performance.setTheater(form.getTheater());
        }
        if (form.getDate() != null) {
            performance.setDate(form.getDate());
        }
        if (form.getTime() != null) {
            performance.setTime(form.getTime());
        }


        return performance;
    }

    public Performance AddNewPerformance(Performance performance) {
        Performance performanceFromDB = performanceRepository.findByTitle(performance.getTitle());
        if (performanceFromDB != null) {
            return null;
        }
        Performance newPerformance = new Performance();
        newPerformance.setTitle(performance.getTitle());
        newPerformance.setGenre(performance.getGenre());
        newPerformance.setTheater(performance.getTheater());
        newPerformance.setDate(performance.getDate());
        newPerformance.setTime(performance.getTime());
        performanceRepository.save(newPerformance);
        return newPerformance;
    }

    public Performance EditPerformance(Performance performance) {
        Performance performanceFromDB = performanceRepository.findById(performance.getId()).get();
        if (performanceFromDB == null) {
            return null;
        }
        performanceRepository.save(performance);
        return performance;
    }

    public void DeletePerformance(Performance performance) {
        Integer performanceId = performanceRepository.findById(performance.getId()).get().getId();
        if (performanceId != null) {
            performanceRepository.deleteById(performanceId);
        }
    }


}
