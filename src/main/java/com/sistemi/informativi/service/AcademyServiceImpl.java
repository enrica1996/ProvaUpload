package com.sistemi.informativi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemi.informativi.entity.Academy;
import com.sistemi.informativi.repository.AcademyRepository;

@Service
public class AcademyServiceImpl implements AcademyService{

	@Autowired
	private AcademyRepository academyRepository;
	
	@Override
	public List<Academy> findAllAcademies() throws Exception {
		
		List<Academy> academies = new ArrayList<>();
		academies = academyRepository.findAll();
		
		if (academies.isEmpty()) {
			
			throw new Exception("Nun ce stanno academies frammt");
		}
		
		return academies;
	}

	@Override
	public Academy findAcademyByCodeNumber(String codeNumber) throws Exception {
		
		return academyRepository.findById(codeNumber).orElseThrow(()->new Exception("Error 404: Academy Not Found"));
	}

	@Override
	public List<Academy> findAllAcademiesByLocation(String location) throws Exception {
		
		List<Academy> academies = new ArrayList<>();
		academies = academyRepository.findByLocation(location);
		
		if (academies.isEmpty()) {
			
			throw new Exception("Nun ci stanno academies in questa location frammt");
		}
		
		return academies;
	}

	@Override
	public List<Academy> findAllAcademiesByStudentsNumberGreatenThan(int studentsNumber) throws Exception {
		
		List<Academy> academies = new ArrayList<>();
		academies = academyRepository.findByStudentsNumberGreaterThan(studentsNumber);
		
		if (academies.isEmpty()) {
			
			throw new Exception("Nun ci stanno academies co più de sti studenti frammt");
		}
		
		return academies;
	}

	@Override
	public Academy saveOrUpdateAcademy(Academy academy) throws Exception {
		
		Academy savedOrUpdatedAcademy = academyRepository.save(academy);
		
		if (!savedOrUpdatedAcademy.getCodeNumber().equals(academy.getCodeNumber())) {
			
			throw new Exception("Bada non ha funzionato il save/update");
		}
		
		return savedOrUpdatedAcademy;
	}

	@Override
	public Map<String, Boolean> removeAcademy(String codeNumber) {
		
		Map<String,Boolean> removeMap = new HashMap<>();
		
		try {
			
			academyRepository.deleteById(codeNumber);
			removeMap.put("deletion", true);
		} catch (IllegalArgumentException ex) {
			
			ex.printStackTrace();
			removeMap.put("deletion", false);
		}
		
		return removeMap;
	}

	@Override
	public List<Academy> findAllAcademiesByLocationAndBusinessName(String location, String businessName) throws Exception {

		List<Academy> academies = new ArrayList<>();
		academies = academyRepository.findByLocationAndBusinessName(location, businessName);
		
		if (academies.isEmpty()) {
			
			throw new Exception("Nun ci stanno academies in questa location con lo stesso nome frammt");
		}
		
		return academies;
	}

}
