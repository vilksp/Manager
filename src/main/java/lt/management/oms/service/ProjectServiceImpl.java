package lt.management.oms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.management.oms.model.Project;
import lt.management.oms.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public List<Project> getAll() {
		return projectRepository.findAll();
	}

	@Override
	public Project findById(Long id) {
		return projectRepository.findById(id).orElseThrow(IllegalStateException::new);
	}

	@Override
	public Project createProject(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public void deleteProjectById(Long id) {
		projectRepository.deleteById(id);
	}

	@Override
	public Project updateProject(Long id, Project newProject) {
		Project oldProject = projectRepository.findById(id).orElseThrow(IllegalStateException::new);
		oldProject.setName(newProject.getName());
		oldProject.setStatus(newProject.getStatus());
		oldProject.setBudget(newProject.getBudget());
		return projectRepository.save(oldProject);
	}
}
