package lt.management.oms.service;

import java.util.List;

import lt.management.oms.model.Project;

public interface ProjectService {

	List<Project> getAll();

	Project findById(Long id);

	Project createProject(Project project);

	void deleteProjectById(Long id);

	Project updateProject(Long id, Project newProject);

}