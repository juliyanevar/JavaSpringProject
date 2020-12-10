package by.nevar.theatre.repository;

import by.nevar.theatre.models.Theater;
import org.springframework.data.repository.CrudRepository;

public interface ITheaterRepository extends CrudRepository<Theater, Integer> {
}
