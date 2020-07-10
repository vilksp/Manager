package lt.management.oms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.management.oms.enums.Status;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    private long duration;
    private Date deadline;

    // private List<Task> tasks = new ArrayList<>();
 
    // For counting duration
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date d1;
    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date d2;

    
	public Project(String name, Status status, double budget) {
		this.name = name;
		this.status = status;
		this.budget = budget;
	}
    
	public long getDuration() {
				duration  = d2.getTime() - d1.getTime();
				long diffInSeconds = TimeUnit.MILLISECONDS.toDays(duration);
				return diffInSeconds;
	}
}
