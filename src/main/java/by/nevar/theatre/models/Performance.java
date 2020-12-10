package by.nevar.theatre.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Performances")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Performance {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="genre")
    private String genre;

    @ManyToOne
    private Theater theater;

    @Column(name="date")
    private Date date;

    @Column(name="time")
    private Time time;

    @ManyToMany
    @JoinTable(name="Performance_actor", joinColumns = @JoinColumn(name="performance_id"),inverseJoinColumns = @JoinColumn(name="actor_id"))
    List<Actor> actors;
}
