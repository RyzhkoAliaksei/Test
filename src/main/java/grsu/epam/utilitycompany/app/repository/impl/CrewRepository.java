package grsu.epam.utilitycompany.app.repository.impl;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import grsu.epam.utilitycompany.app.domain.Crew;
import grsu.epam.utilitycompany.app.domain.Demand;
import grsu.epam.utilitycompany.app.repository.CrewDao;
import grsu.epam.utilitycompany.app.repository.base.AbstractHibernateDao;

@Repository
public class CrewRepository extends AbstractHibernateDao<Crew, Long> implements
		CrewDao {

	@Override
	public List<Crew> findFreeCrews(List<Long> demands) {

		Criteria cr = getSession().createCriteria(Crew.class)
				.createCriteria("demands")
				.add(Restrictions.not(Restrictions.in("demandId", demands)));

		List<Crew> crews = (List<Crew>) cr.list();

		Criteria cr1 = getSession().createCriteria(Crew.class).add(
				Restrictions.isEmpty("demands"));
		List<Crew> crews1 = (List<Crew>) cr1.list();
		crews.addAll(crews1);
		return crews;
	}
}
