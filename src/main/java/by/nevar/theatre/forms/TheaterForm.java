package by.nevar.theatre.forms;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterForm {
    private Integer id;
    private String name;
}
