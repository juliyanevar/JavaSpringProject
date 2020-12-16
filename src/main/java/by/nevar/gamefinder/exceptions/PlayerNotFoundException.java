package by.nevar.gamefinder.exceptions;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(int id) {
        super("Could not find user " + id);
    }
}
