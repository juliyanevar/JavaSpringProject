package by.nevar.gamefinder.models;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "championships")
@Getter
public class Championship extends BaseEntity{
    public Championship() {
    }

    public Championship(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String subject) {
        this.name = subject;
    }
}