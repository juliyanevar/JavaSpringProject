package by.nevar.gamefinder.service.serviceInterfaces;

import by.nevar.gamefinder.models.User;

import java.util.List;

public interface IUserService {
    User register(User user);
    List<User> getAll();
    User findByUsername(String userName);
    User findByEmail(String userName);
    User findById(Long id);
    void delete(Long id);
}
