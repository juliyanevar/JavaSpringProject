package by.nevar.gamefinder.repository;

import by.nevar.gamefinder.models.Request;
import by.nevar.gamefinder.models.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface IChampionshipRepository extends CrudRepository<Request, Integer> {
    Request findByTitle(String title);
    List<Request> findByGenre(String genre);
    List<Request> findByTheater(Status status);
    List<Request> findByDate(Date date);
    List<Request> findByTime(Time time);
}
