package by.nevar.gamefinder.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Request {
    @Id
    @GeneratedValue
    private int id;
    private String message;
    private String status;

    @ManyToOne
    private User user;
    @ManyToOne
    private Championship championship;

    public Request(){
    }

    public Request(String message, String status, User user, Championship championship) {
        this.message = message;
        this.status = status;
        this.user = user;
        this.championship = championship;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }
}
