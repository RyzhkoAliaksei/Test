package grsu.epam.utilitycompany.app.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import grsu.epam.utilitycompany.app.domain.Speciality;
import grsu.epam.utilitycompany.app.repository.SpecialityDao;
import grsu.epam.utilitycompany.app.repository.base.AbstractHibernateDao;

@Repository
public class SpecialityRepository extends
		AbstractHibernateDao<Speciality, Long> implements SpecialityDao {

	@Override
	public List<Speciality> getAllS() {
		final List<Speciality> specialities = getSession().createQuery(
				"from Speciality").list();
		return specialities;
	}
	
}
