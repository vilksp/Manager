package lt.management.oms.service;

import lt.management.oms.model.Project;

import java.util.List;

public interface ProjectService {

	List<Project> getAll();

	Project findById(Long id);

	Project createProject(Project project);

	void deleteProjectById(Long id);

	Project updateProject(Long id, Project newProject);

}