package grsu.epam.utilitycompany.app.service;

import grsu.epam.utilitycompany.app.domain.Crew;
import grsu.epam.utilitycompany.app.domain.Demand;
import grsu.epam.utilitycompany.app.service.exception.DateIsEmptyException;

import java.util.List;

public interface CrewService {
	
	void saveCrew(Crew crew);

	void deleteCrew(Crew crew);

	Crew getCrewByID(Long crewId);

	List<Crew> getCrews();
	
	List<Crew> getFreeCrews(Demand demand) throws DateIsEmptyException;
	

	void updateCrew(Crew crew);

	

}
