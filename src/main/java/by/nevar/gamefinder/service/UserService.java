package by.nevar.gamefinder.service;

import by.nevar.gamefinder.models.Role;
import by.nevar.gamefinder.models.User;
import by.nevar.gamefinder.repository.IRoleRepository;
import by.nevar.gamefinder.repository.IUserRepository;
import by.nevar.gamefinder.service.serviceInterfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService implements IUserService {
    private IUserRepository userRepository;
    private IRoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepository, IRoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUserName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_PLAYER");

        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        user.setCreated(new Date());
        user.setUpdated(new Date());
        log.info("{} registered", user);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        log.info("UserService : getAll");
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        log.info("UserService : findByUsername");
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String mail) {
        log.info("UserService : findByEmail");
        return userRepository.findByEmail(mail);
    }

    @Override
    public User findById(Long id) {
        log.info("UserService : findById");
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        log.info("UserService : delete");
        userRepository.deleteById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}

