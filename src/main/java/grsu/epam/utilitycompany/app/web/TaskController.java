package grsu.epam.utilitycompany.app.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import grsu.epam.utilitycompany.app.domain.Demand;
import grsu.epam.utilitycompany.app.domain.Task;
import grsu.epam.utilitycompany.app.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("task")
public class TaskController {
	@Autowired
	private TaskService taskService;

	@PreAuthorize("isFullyAuthenticated() and hasAnyRole('ROLE_DISPATCHER','ROLE_CLIENT')")
	@RequestMapping(value = "tasks/{demandId}", method = RequestMethod.GET)
	public String showTasksbyDemand(Model model,
			@PathVariable("demandId") Demand demand) {
		model.addAttribute("tasks", taskService.getTasksByDemand(demand));
		model.addAttribute("demand", demand);
		return "task.list";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/new/for-demand/{demandId}", method = RequestMethod.GET)
	public String newTaskForm(Model model,
			@PathVariable("demandId") Demand demand) {
		Task task = new Task();
		model.addAttribute("task", task);
		model.addAttribute("action", demand.getDemandId());
		return "task.form";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/new/for-demand/{demandId}", method = RequestMethod.POST)
	public String newTaskSubmit(@Valid @ModelAttribute Task task,
			BindingResult result, @PathVariable("demandId") Demand demand,
			Model model) {
		String view = "redirect:/task/tasks/{demandId}";
		if (result.hasErrors()) {
			view = "task.form";
		} else {
		
				task.setDemand(demand);
				taskService.saveTask(task);
				model.addAttribute("tasks",
						taskService.getTasksByDemand(task.getDemand()));
			model.asMap().remove("task");
			model.addAttribute("demandId", demand.getDemandId());
		}
		return view;
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/edit/{taskId}", method = RequestMethod.GET)
	public String editTaskForm(@PathVariable("taskId") Task task,
			HttpSession session, Model model) {
		model.addAttribute("task", task);
		model.addAttribute("action", task.getTaskId());
		return "task.form";
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/edit/{taskId}", method = RequestMethod.POST)
	public String editTaskSubmit(@Valid @ModelAttribute Task task,BindingResult result, Model model) {
		String view = "redirect:/task/tasks/{demandId}";
		if (result.hasErrors()) {
			view = "task.form";
		} else {
			taskService.updateTask(task);
			System.out.println(task.getDemand().getDemandId());
			model.addAttribute("tasks",
					taskService.getTasksByDemand(task.getDemand()));
			model.addAttribute("demandId", task.getDemand().getDemandId());
		}
		return view;
	}

	@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/delete/{taskId}", method = RequestMethod.GET)
	public String deleteTask(@PathVariable("taskId") Task task, Model model) {
		Demand demand = task.getDemand();
		taskService.deleteTask(task);
		model.addAttribute("tasks", taskService.getTasksByDemand(demand));
		model.addAttribute("demandId", demand.getDemandId());
		return "redirect:/task/tasks/{demandId}";
	}
}
