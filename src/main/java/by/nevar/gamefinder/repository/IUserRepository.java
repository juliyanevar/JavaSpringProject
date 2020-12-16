package by.nevar.gamefinder.repository;

import by.nevar.gamefinder.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String UserName);
    User findByEmail(String UserName);
    <S extends User> S save(S s);
}
