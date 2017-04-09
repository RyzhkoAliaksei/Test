package grsu.epam.utilitycompany.app.repository;

import java.util.List;

import grsu.epam.utilitycompany.app.domain.Speciality;
import grsu.epam.utilitycompany.app.repository.base.GenericDao;

public interface SpecialityDao extends GenericDao<Speciality, Long> {

	List<Speciality> getAllS();
		
	}
