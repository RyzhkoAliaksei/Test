package grsu.epam.utilitycompany.app.service;

import grsu.epam.utilitycompany.app.domain.Speciality;

import java.util.List;

public interface SpecialityService {

	void saveSpeciality(Speciality speciality);

	void deleteSpeciality(Speciality speciality);

	Speciality getSpecialityByID(Long specialityId);

	List<Speciality> getSpecialities();

	void updateSpeciality(Speciality speciality);
}
