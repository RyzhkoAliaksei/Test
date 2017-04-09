package grsu.epam.utilitycompany.app.service.impl;

import grsu.epam.utilitycompany.app.domain.Crew;
import grsu.epam.utilitycompany.app.domain.Demand;
import grsu.epam.utilitycompany.app.repository.CrewDao;
import grsu.epam.utilitycompany.app.repository.DemandDao;
import grsu.epam.utilitycompany.app.service.CrewService;
import grsu.epam.utilitycompany.app.service.exception.DateIsEmptyException;
import grsu.epam.utilitycompany.app.service.exception.IncorrectPeriodTimeException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CrewServiceImpl implements CrewService {

	@Autowired
	private CrewDao crewRepository;
	@Autowired
	private DemandDao demandRepository;

	@Override
	public void saveCrew(Crew crew) {
		crewRepository.save(crew);
	}

	@Override
	public void deleteCrew(Crew crew) {
		crewRepository.delete(crew);
	}

	@Override
	public void updateCrew(Crew crew) {
		crewRepository.update(crew);
	}

	@Override
	public Crew getCrewByID(Long CrewId) {

		return crewRepository.findById(CrewId);
	}

	@Override
	public List<Crew> getCrews() {

		List<Crew> crews = crewRepository.findAll();
		return crews;
	}

	@Override
	public List<Crew> getFreeCrews(Demand demand) throws DateIsEmptyException {
		List<Crew> crews=null;
		if ((demand.getBeginTime() != null) && (demand.getDemandId() != null)) {
			List<Long> demands = demandRepository.findBusyTime(demand);
			crews = crewRepository.findFreeCrews(demands);
		} else {
			throw new DateIsEmptyException(
					"at first enter time period");
		}

		return crews;
	}

}
