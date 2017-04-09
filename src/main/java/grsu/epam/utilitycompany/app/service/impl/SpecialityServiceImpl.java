package grsu.epam.utilitycompany.app.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import grsu.epam.utilitycompany.app.domain.Speciality;
import grsu.epam.utilitycompany.app.repository.SpecialityDao;
import grsu.epam.utilitycompany.app.service.SpecialityService;

@Service
@Transactional
public class SpecialityServiceImpl  implements SpecialityService {

	@Autowired
	private SpecialityDao specialityRepository;

	@Override
	public void deleteSpeciality(Speciality speciality) {
	specialityRepository.delete(speciality);
		
	}

	@Override
	public Speciality getSpecialityByID(Long specialityId) {
		return specialityRepository.findById(specialityId);
	}

	@Override
	public List<Speciality> getSpecialities() {
		List<Speciality> specialities = specialityRepository.findAll();		
		return specialities;
	}

	@Override
	public void updateSpeciality(Speciality speciality) {
		specialityRepository.update(speciality);
		
	}

	@Override
	public void saveSpeciality(Speciality speciality) {
		specialityRepository.save(speciality);
		
	}
}
