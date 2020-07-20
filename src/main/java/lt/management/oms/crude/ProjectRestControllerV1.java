package lt.management.oms.crude;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lt.management.oms.dto.ProjectDto;
import lt.management.oms.model.Project;
import lt.management.oms.service.ProjectService;

@RestController
@RequestMapping("api/v1/projects")
@CrossOrigin(origins = { "http://localhost:3000" })
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