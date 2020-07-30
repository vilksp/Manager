package lt.management.oms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import lt.management.oms.exceptions.ProjectNotFoundException;
import lt.management.oms.model.Project;
import lt.management.oms.repository.ProjectRepository;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public List<Project> getAll() {
		log.info("Returned all the projects");
		return projectRepository.findAll();
	}

	@Override
	public Project findById(Long id) {
		log.info("The project found by id: {}", id);
		return projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
	}

	@Override
	public Project createProject(Project project) {
		log.info("The project successfully created");
		return projectRepository.save(project);
	}

	@Override
	public void deleteProjectById(Long id) {
		log.info("The project deleted by id: {}", id);
		projectRepository.deleteById(id);
	}

	@Override
	public Project updateProject(Long id, Project newProject) {
		Project oldProject = projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
		oldProject.setName(newProject.getName());
		oldProject.setStatus(newProject.getStatus());
		oldProject.setBudget(newProject.getBudget());
		oldProject.setAddress(newProject.getAddress());
		oldProject.setDeadline(newProject.getDeadline());
		log.info("The project successfully updated");
		return projectRepository.save(oldProject);
	}
}
