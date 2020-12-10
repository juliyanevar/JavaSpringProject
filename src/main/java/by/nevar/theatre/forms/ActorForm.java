package by.nevar.theatre.forms;

import by.nevar.theatre.models.Performance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorForm {
    private Integer id;
    private String name;
    List<Performance> performances;
}
