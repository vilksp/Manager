package lt.management.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.management.oms.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
