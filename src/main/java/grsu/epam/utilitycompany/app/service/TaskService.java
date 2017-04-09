package grsu.epam.utilitycompany.app.service;

import grsu.epam.utilitycompany.app.domain.Demand;
import grsu.epam.utilitycompany.app.domain.Task;

import java.util.List;

public interface TaskService {
	
	void saveTask(Task task);
	
	void deleteTask(Task task);
	
	Task getTaskByID(Long taskId);
	
	List<Task> getTasks();
	
	List<Task> getTasksByDemand(Demand demand);

	void updateTask(Task task);
}
