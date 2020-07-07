package lt.management.oms.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseEntity {


    private String firstName;
    private String lastName;

    // Needs to include speciality(Another class)

    //Needsto include address (another class)

    //Needs to include project(another class)

}
