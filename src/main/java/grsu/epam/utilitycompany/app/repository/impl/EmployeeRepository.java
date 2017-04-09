package grsu.epam.utilitycompany.app.repository.impl;

import java.util.List;

import grsu.epam.utilitycompany.app.domain.Crew;
import grsu.epam.utilitycompany.app.domain.Employee;
import grsu.epam.utilitycompany.app.domain.Speciality;
import grsu.epam.utilitycompany.app.repository.EmployeeDao;
import grsu.epam.utilitycompany.app.repository.base.AbstractHibernateDao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository extends AbstractHibernateDao<Employee, Long>
		implements EmployeeDao {

	@Override
	public List<Employee> findEmployeesByCrew(Crew crew) {
		Criteria cr = getSession().createCriteria(Employee.class).add(
				Restrictions.eq("crew", crew));
		return (List<Employee>) cr.setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public void updateFromCrew(Employee employee) {

		if (employee.getCrew().getCrewId() == 0) {
			String hqlUpdate = "update Employee  set CREW_ID = null where EMPLOYEE_ID = :id";
			getSession().createQuery(hqlUpdate)
					.setLong("id", employee.getId()).executeUpdate();
		} else {
			String hqlUpdate = "update Employee  set CREW_ID = :crewId where EMPLOYEE_ID = :id";
			getSession().createQuery(hqlUpdate)
					.setLong("crewId", employee.getCrew().getCrewId())
					.setLong("id", employee.getId()).executeUpdate();
		}
	}

	@Override
	public List<Employee> findEmployeesByCrew(Speciality speciality) {
		Criteria cr = getSession().createCriteria(Employee.class)
				.add(Restrictions.eq("speciality", speciality))
				.add(Restrictions.isNull("crew"));
		return (List<Employee>) cr.setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY).list();

	}
}
