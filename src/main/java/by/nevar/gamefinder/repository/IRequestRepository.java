package by.nevar.gamefinder.repository;

import by.nevar.gamefinder.models.Championship;
import by.nevar.gamefinder.models.Request;
import by.nevar.gamefinder.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface IRequestRepository  extends JpaRepository<Request, Integer>  {
    Request findByUserAndAndChampionship(User user, Championship championship);
    List<Request> findAllByUserAndAndChampionship(User user, Championship championship);
    <S extends User> S save(S s);
}
