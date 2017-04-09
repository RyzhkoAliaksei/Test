package grsu.epam.utilitycompany.app.repository;

import java.util.List;

import grsu.epam.utilitycompany.app.domain.Demand;
import grsu.epam.utilitycompany.app.domain.Task;
import grsu.epam.utilitycompany.app.repository.base.GenericDao;

public interface TaskDao extends GenericDao<Task, Long>{
	List<Task> findTasksByDemand(Demand demand);

}
