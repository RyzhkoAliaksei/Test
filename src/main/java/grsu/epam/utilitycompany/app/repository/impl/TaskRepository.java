package grsu.epam.utilitycompany.app.repository.impl;

import java.util.List;

import grsu.epam.utilitycompany.app.domain.Demand;
import grsu.epam.utilitycompany.app.domain.Task;
import grsu.epam.utilitycompany.app.repository.TaskDao;
import grsu.epam.utilitycompany.app.repository.base.AbstractHibernateDao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository extends AbstractHibernateDao<Task, Long> implements
		TaskDao {

	@Override
	public List<Task> findTasksByDemand(Demand demand) {
		Criteria cr = getSession().createCriteria(Task.class).add(
				Restrictions.eq("demand", demand));
		return (List<Task>) cr.setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY).list();

	}

}
