package by.nevar.theatre.repository;

import by.nevar.theatre.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends CrudRepository<Role, Integer> {
}
