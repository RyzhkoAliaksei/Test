package grsu.epam.utilitycompany.app.service;

import grsu.epam.utilitycompany.app.domain.Client;
import grsu.epam.utilitycompany.app.domain.Demand;
import grsu.epam.utilitycompany.app.domain.Status;
import grsu.epam.utilitycompany.app.service.exception.IncorrectEditTimeException;
import grsu.epam.utilitycompany.app.service.exception.IncorrectPeriodTimeException;

import java.util.List;

public interface DemandService {
	
	void saveDemand(Demand demand);
	
	void deleteDemand(Demand demand);
	
	Demand getDemandByID(Long demandId);
	
	List<Demand> getDemands();

	void updateDemand(Demand demand);

	List<Demand> getDemandsByClient(Client client);

	List<Demand> getDemandsByStatus(Status status);

	void updateStatus(Demand demand);

	List<Demand> getDemandsBySurname(String surname);

	void updateTime(Demand demand) throws IncorrectPeriodTimeException, IncorrectEditTimeException;

	void updateCrew(Demand demand);

	void deleteCrew(Demand demand);
}
