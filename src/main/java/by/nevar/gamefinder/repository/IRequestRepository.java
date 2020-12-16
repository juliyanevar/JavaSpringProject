package by.nevar.gamefinder.repository;

import by.nevar.gamefinder.models.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRequestRepository extends CrudRepository<Status, Integer> {
    Status findByName(String name);
    List<Status> findAllByName(String name);
}
