package by.nevar.theatre.repository;

import by.nevar.theatre.models.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActorRepository extends CrudRepository<Actor, Integer> {
    Actor findByName(String name);
}
