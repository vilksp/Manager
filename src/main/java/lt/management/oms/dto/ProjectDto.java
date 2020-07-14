package lt.management.oms.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lt.management.oms.enums.Status;
import lt.management.oms.model.Address;
import lt.management.oms.model.Project;

@Data
@Setter
@Getter
public class ProjectDto {

	private String name;
	private Status status;
	private double budget;
	private Address address;
	private Date deadline;

	public Project build() {
		return new Project(name, status, budget, address, deadline);
	}

}
