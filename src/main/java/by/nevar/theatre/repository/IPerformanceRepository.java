package by.nevar.theatre.repository;

import by.nevar.theatre.models.Actor;
import by.nevar.theatre.models.Performance;
import by.nevar.theatre.models.Theater;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface IPerformanceRepository extends CrudRepository<Performance, Integer> {
    Performance findByTitle(String title);
    List<Performance> findByGenre(String genre);
    List<Performance> findByTheater(Theater theater);
    List<Performance> findByDate(Date date);
    List<Performance> findByTime(Time time);
}
