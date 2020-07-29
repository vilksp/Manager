package lt.management.oms.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lt.management.oms.enums.Priority;
import lt.management.oms.enums.Status;
import lt.management.oms.model.Task;

@Data
@Setter
@Getter
public class TaskDto {

	private String taskName;
	private String taskDescription;
	private Status status;
	private Priority priority;

	public Task build() {
		Task task = new Task();
		task.setTaskName(taskName);
		task.setTaskDescription(taskDescription);
		task.setStatus(status);
		task.setPriority(priority);
		task.setUpdateDate(LocalDate.now());
		task.setCreateDate(LocalDate.now());
		return task;

	}
}
