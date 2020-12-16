package by.nevar.gamefinder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }
    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
