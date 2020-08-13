package lt.management.oms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address extends BaseEntity {

    private String city;
    private String street;
    private Integer street_number;
    private Integer house_number;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Project project;

}
