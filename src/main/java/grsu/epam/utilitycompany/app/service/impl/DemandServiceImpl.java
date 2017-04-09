package grsu.epam.utilitycompany.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import grsu.epam.utilitycompany.app.domain.Client;
import grsu.epam.utilitycompany.app.domain.Demand;
import grsu.epam.utilitycompany.app.domain.Status;
import grsu.epam.utilitycompany.app.repository.DemandDao;
import grsu.epam.utilitycompany.app.service.DemandService;
import grsu.epam.utilitycompany.app.service.exception.IncorrectEditTimeException;
import grsu.epam.utilitycompany.app.service.exception.IncorrectPeriodTimeException;

@Service
@Transactional
public class DemandServiceImpl implements DemandService {
	@Autowired
	private DemandDao demandRepository;

	@Override
	public void saveDemand(Demand demand) {
		demandRepository.save(demand);

	}

	@Override
	public void deleteDemand(Demand demand) {
		demandRepository.delete(demand);
	}

	@Override
	public Demand getDemandByID(Long demandId) {
		return demandRepository.findById(demandId);
	}

	@Override
	public List<Demand> getDemands() {
		List<Demand> demands = demandRepository.findAll();
		return demands;
	}

	@Override
	public void updateDemand(Demand demand) {
		demandRepository.update(demand);

	}

	@Override
	public List<Demand> getDemandsByClient(Client client) {
		List<Demand> demands = demandRepository.findDemandsByClient(client);
		return demands;
	}

	@Override
	public List<Demand> getDemandsByStatus(Status status) {
		List<Demand> demands = demandRepository.findDemandsByStatus(status);
		return demands;
	}

	@Override
	public void updateStatus(Demand demand) {
		demandRepository.updateStatus(demand);

	}

	@Override
	public List<Demand> getDemandsBySurname(String surname) {
		List<Demand> demands = demandRepository.findDemandsBySurname(surname);
		return demands;
	}

	@Override
	public void updateTime(Demand demand) throws IncorrectPeriodTimeException,
			IncorrectEditTimeException {

		if (demandRepository.findById(demand.getDemandId()).getCrew() == null) {

			if (((demand.getBeginTime()!=null)&&(demand.getEndTime()!=null))&&(demand.getBeginTime().before(demand.getEndTime()))) {
				demandRepository.updateTime(demand);
			} else {
				throw new IncorrectPeriodTimeException(
						"start date has to be less than a date of the end");
			}
		} else {
			throw new IncorrectEditTimeException(
					"at first remove crew then edit time");
		}

	}

	@Override
	public void updateCrew(Demand demand) {
		demandRepository.updateCrew(demand);

	}

	@Override
	public void deleteCrew(Demand demand) {
		demandRepository.deleteCrew(demand);

	}

}
