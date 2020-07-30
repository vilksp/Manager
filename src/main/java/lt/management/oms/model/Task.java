package lt.management.oms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.management.oms.enums.Priority;
import lt.management.oms.enums.Status;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Deprecated
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
    private Project project;

}
