package by.nevar.theatre.forms;

import by.nevar.theatre.models.Actor;
import by.nevar.theatre.models.Theater;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceForm {
    private Integer id;
    private String title;
    private String genre;
    private Theater theater;
    private Date date;
    private Time time;
    List<Actor> actors;
}
