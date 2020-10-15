package by.nevar.theatre.forms;

import by.nevar.theatre.models.Actor;
import by.nevar.theatre.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceForm {
    private String name;
    private Date date;
    private Time time;
    private Genre genre;
    private List<Actor> listActors;
}
