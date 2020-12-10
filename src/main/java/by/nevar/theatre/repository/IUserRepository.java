package by.nevar.theatre.repository;

import by.nevar.theatre.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {
}
