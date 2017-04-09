package grsu.epam.utilitycompany.app.repository;


import java.util.List;

import grsu.epam.utilitycompany.app.domain.Client;
import grsu.epam.utilitycompany.app.domain.Demand;
import grsu.epam.utilitycompany.app.domain.Status;
import grsu.epam.utilitycompany.app.repository.base.GenericDao;

public interface DemandDao extends GenericDao<Demand, Long> {

	List<Demand> findDemandsByClient(Client client);

	List<Demand> findDemandsByStatus(Status status);

	void updateStatus(Demand demand);

	List<Long> findBusyTime(Demand demand);

	List<Demand> findDemandsBySurname(String surname);

	void updateTime(Demand demand);

	void updateCrew(Demand demand);

	void deleteCrew(Demand demand);

}
