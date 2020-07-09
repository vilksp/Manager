package lt.management.oms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.management.oms.enums.Status;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project extends BaseEntity {

    private String name;
    private Status status;
    // private Client client;
    private double budget;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    // private Role projectManager;
    private int duration;
    private Date deadline;

    // private List<Task> tasks = new ArrayList<>();

}
