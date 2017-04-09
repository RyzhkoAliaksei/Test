package grsu.epam.utilitycompany.app.web.converters;

import grsu.epam.utilitycompany.app.domain.Task;
import grsu.epam.utilitycompany.app.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToTaskConverter implements Converter<String, Task> {

	@Autowired
	private TaskService taskService;

	@Override
	public Task convert(String taskId) {

		return taskService.getTaskByID(new Long(taskId));
	}

}