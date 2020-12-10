package by.nevar.theatre.forms;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleForm {
    private Integer id;
    private String name;
}
