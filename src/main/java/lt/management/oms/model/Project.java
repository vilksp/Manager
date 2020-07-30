package lt.management.oms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.management.oms.enums.Status;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    // private Client client;
    private double budget;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    // private Role projectManager;
    private long duration;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    // For counting duration
    @CreationTimestamp
    private LocalDate startDate;
    @UpdateTimestamp
    private LocalDate endDate;

//    public Project(String name, Status status, double budget, Address address, LocalDate deadline) {
//        this.name = name;
//        this.status = status;
//        this.budget = budget;
//        this.address = address;
//        this.deadline = deadline;
//    }

//    public long getDuration() {
//        duration = endDate.getTime() - startDate.getTime();
//        long diffInSeconds = TimeUnit.MILLISECONDS.toDays(duration);
//        return diffInSeconds;
//    }


}
