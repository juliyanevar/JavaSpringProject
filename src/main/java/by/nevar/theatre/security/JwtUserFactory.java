package by.nevar.theatre.security;

import by.nevar.theatre.models.User;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static User create(User user) {
        return new User(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                true,
                user.getRoles()
        );
    }
}

