package by.nevar.theatre.repository;

import by.nevar.theatre.models.Performance;
import org.springframework.data.repository.CrudRepository;

public interface IPerformanceRepository extends CrudRepository<Performance, Integer> {
}
