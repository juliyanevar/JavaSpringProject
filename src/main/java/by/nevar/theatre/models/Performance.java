package by.nevar.theatre.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Performance {
    private String name;
    private Date date;
    private Time time;
    private Genre genre;
    private List<Actor> listActors;
}
