package grsu.epam.utilitycompany.app.service.impl;

import java.util.List;

import grsu.epam.utilitycompany.app.domain.Demand;
import grsu.epam.utilitycompany.app.domain.Task;
import grsu.epam.utilitycompany.app.repository.TaskDao;
import grsu.epam.utilitycompany.app.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskDao taskRepository;

	@Override
	public void saveTask(Task task) {
		taskRepository.save(task);
		
	}

	@Override
	public void deleteTask(Task task) {
		taskRepository.delete(task);
		
	}

	@Override
	public Task getTaskByID(Long taskId) {
		return taskRepository.findById(taskId);
	}

	@Override
	public List<Task> getTasks() {
		List<Task> tasks = taskRepository.findAll();		
		return tasks;
	}
	@Override
	public void updateTask(Task task) {
		taskRepository.update(task);
		
	}

	

	@Override
	public List<Task> getTasksByDemand(Demand demand) {
		List<Task> tasks = taskRepository.findTasksByDemand(demand);		
		return tasks;
	}
}
