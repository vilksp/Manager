package lt.management.oms.dto;

import lombok.Getter;
import lombok.Setter;
import lt.management.oms.enums.Status;
import lt.management.oms.model.Address;
import lt.management.oms.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Component
public class ProjectDto {

    private String name;
    private Status status;
    private double budget;
    private Address address;
    private LocalDate deadline;

    public Project build() {
        Project project = new Project();
        project.setName(name);
        project.setStatus(status);
        project.setBudget(budget);
        project.setAddress(address);
        project.setDeadline(deadline);
        project.setUpdateDate(LocalDate.now());
        project.setCreateDate(LocalDate.now());
        return project;


    }

    public Project build(Project projectToDto) {
        Project project = new Project();
        project.setName(projectToDto.getName());
        project.setStatus(projectToDto.getStatus());
        project.setBudget(projectToDto.getBudget());
        project.setAddress(projectToDto.getAddress());
        project.setDeadline(projectToDto.getDeadline());
        project.setUpdateDate(LocalDate.now());
        project.setCreateDate(LocalDate.now());
        return project;


    }


    public List<Project> entityToDto(Page<Project> projectList) {
        List<Project> list = new ArrayList();
        projectList.stream().map(x -> build(x)).forEach(x -> list.add(x));
        return list;
    }


}
