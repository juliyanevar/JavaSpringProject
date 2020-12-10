package by.nevar.theatre.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Column(name="title")
    private String title;

    @NotBlank
    @Column(name="genre")
    private String genre;

    @NotNull
    @ManyToOne
    private Theater theater;

    @NotBlank
    @Column(name="date")
    private Date date;

    @NotBlank
    @Column(name="time")
    private Time time;

    @NotEmpty
    @ManyToMany
    @JoinTable(name="Performance_actor", joinColumns = @JoinColumn(name="performance_id"),inverseJoinColumns = @JoinColumn(name="actor_id"))
    List<Actor> actors;
}
