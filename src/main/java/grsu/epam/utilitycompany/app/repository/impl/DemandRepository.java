package grsu.epam.utilitycompany.app.repository.impl;

import java.util.List;

import grsu.epam.utilitycompany.app.domain.Client;
import grsu.epam.utilitycompany.app.domain.Demand;
import grsu.epam.utilitycompany.app.domain.Status;
import grsu.epam.utilitycompany.app.repository.DemandDao;
import grsu.epam.utilitycompany.app.repository.base.AbstractHibernateDao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class DemandRepository extends AbstractHibernateDao<Demand, Long>
		implements DemandDao {

	@Override
	public List<Demand> findDemandsByClient(Client client) {
		Criteria cr = getSession().createCriteria(Demand.class).add(
				Restrictions.eq("client", client));
		return (List<Demand>) cr.setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY).list();

	}

	@Override
	public List<Demand> findDemandsByStatus(Status status) {
		Criteria cr = getSession().createCriteria(Demand.class).add(
				Restrictions.eq("status", status));
		return (List<Demand>) cr.setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY).list();

	}

	@Override
	public void updateStatus(Demand demand) {
		String hqlUpdate = "update Demand  set status = :newStatus where demandId = :demandId";
		int updatedEntities = getSession().createQuery(hqlUpdate)
				.setInteger("newStatus", demand.getStatus().ordinal())
				.setLong("demandId", demand.getDemandId()).executeUpdate();
	}
	
	@Override
	public void deleteCrew(Demand demand) {
		String hqlUpdate = "update Demand  set crew_id = null where demandId = :demandId";
		getSession().createQuery(hqlUpdate)
				.setLong("demandId", demand.getDemandId()).executeUpdate();
		
	}
	
	@Override
	public List<Long> findBusyTime(Demand demand) {
		
		Disjunction filterDisjunction = Restrictions.disjunction();
		filterDisjunction.add(Restrictions.and(
				Restrictions.le("beginTime", demand.getBeginTime()),
				Restrictions.ge("endTime", demand.getBeginTime())));
		filterDisjunction.add(Restrictions.and(
				Restrictions.le("beginTime", demand.getEndTime()),
				Restrictions.ge("endTime", demand.getEndTime())));
		filterDisjunction.add(Restrictions.between("beginTime",
				demand.getBeginTime(), demand.getEndTime()));
		Criteria cr = getSession().createCriteria(Demand.class)
				.add(filterDisjunction)
				.setProjection(Projections.property("demandId"));
		List<Long> demands = cr.list();
		return demands;
	}

	@Override
	public List<Demand> findDemandsBySurname(String surname) {
		Criteria cr = getSession().createCriteria(Demand.class)
				.createCriteria("client")
				.add(Restrictions.eq("surname", surname));
		return (List<Demand>) cr.setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public void updateTime(Demand demand) {
		String hqlUpdate = "update Demand  set begin_time = :beginTime,end_time=:endTime where demandId = :demandId";
		getSession().createQuery(hqlUpdate)
				.setParameter("endTime", demand.getEndTime())
				.setParameter("beginTime", demand.getBeginTime())
				.setLong("demandId", demand.getDemandId()).executeUpdate();

	}

	@Override
	public void updateCrew(Demand demand) {
		String hqlUpdate = "update Demand  set crew_id = :crewId where demandId = :demandId";
		getSession().createQuery(hqlUpdate)
				.setParameter("crewId", demand.getCrew().getCrewId())
				.setLong("demandId", demand.getDemandId()).executeUpdate();

	}



}
