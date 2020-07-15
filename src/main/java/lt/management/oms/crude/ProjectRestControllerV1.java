package lt.management.oms.crude;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.management.oms.dto.ProjectDto;
import lt.management.oms.model.Project;
import lt.management.oms.service.ProjectService;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectRestControllerV1 {

	@Autowired
	private ProjectService projectService;

	@GetMapping
	public List<Project> getAllProjects() {
		return projectService.getAll();
	}

	@GetMapping("/{projectId}")
	public Project getProjectById(@PathVariable Long projectId) {
		return projectService.findById(projectId);
	}

	@PostMapping
	public Project createProject(@RequestBody ProjectDto project) {
		return projectService.createProject(project.build());
	}

	@DeleteMapping("/{projectId}")
	public void deleteProjectById(@PathVariable Long projectId) {
		projectService.deleteProjectById(projectId);
	}

	@PutMapping("/{projectId}")
	public Project updateProjectById(@PathVariable Long projectId, @RequestBody ProjectDto project) {
		return projectService.updateProject(projectId, project.build());
	}
}