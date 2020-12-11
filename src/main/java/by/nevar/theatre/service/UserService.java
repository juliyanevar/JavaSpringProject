package by.nevar.theatre.service;

import by.nevar.theatre.models.Role;
import by.nevar.theatre.models.User;
import by.nevar.theatre.repository.IUserRepository;
import by.nevar.theatre.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        User jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }

    public User registrate (User user){
        User userFromDB = (User)userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return null;
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setActive(true);
        newUser.setRoles(Collections.singleton(Role.USER));
        if (userRepository.count()==0){
            Set<Role> roles = new HashSet<>();
            roles.add(Role.ADMIN);
            roles.add(Role.USER);
            newUser.setRoles(roles);
        }
        userRepository.save(newUser);
        return newUser;
    }


}
