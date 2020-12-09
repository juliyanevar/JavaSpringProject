package by.nevar.theatre.repository;

import by.nevar.theatre.models.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Integer> {
}
