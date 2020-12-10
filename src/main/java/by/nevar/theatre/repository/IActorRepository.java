package by.nevar.theatre.repository;

import by.nevar.theatre.models.Actor;
import org.springframework.data.repository.CrudRepository;

public interface IActorRepository extends CrudRepository<Actor, Integer> {
}
