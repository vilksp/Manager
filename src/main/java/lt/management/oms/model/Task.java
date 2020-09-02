package lt.management.oms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.management.oms.enums.Priority;
import lt.management.oms.enums.Status;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task extends BaseEntity {

    private String taskName;
    private String taskDescription;
    private Status status;
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Project project;

}
