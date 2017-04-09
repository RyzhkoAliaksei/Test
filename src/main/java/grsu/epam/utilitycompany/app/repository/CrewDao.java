package grsu.epam.utilitycompany.app.repository;

import java.util.List;

import grsu.epam.utilitycompany.app.domain.Crew;
import grsu.epam.utilitycompany.app.repository.base.GenericDao;

public interface CrewDao extends GenericDao<Crew, Long> {
	List<Crew> findFreeCrews(List<Long> demands);
}
