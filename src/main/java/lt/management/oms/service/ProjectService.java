package lt.management.oms.service;

import lt.management.oms.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

    Page<Project> getAll(Pageable page);

    Project findById(Long id);

    Project createProject(Project project);

    void deleteProjectById(Long id);

    Project updateProject(Long id, Project newProject);

}