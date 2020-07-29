package lt.management.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.management.oms.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
