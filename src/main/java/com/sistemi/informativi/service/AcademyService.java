package com.sistemi.informativi.service;
import java.util.List;
import java.util.Map;

import com.sistemi.informativi.entity.Academy;

public interface AcademyService {

	public List<Academy> findAllAcademies() throws Exception;
	
	public Academy findAcademyByCodeNumber(String codeNumber) throws Exception;
	
	public List<Academy> findAllAcademiesByLocation(String location) throws Exception;
	
	public List<Academy> findAllAcademiesByStudentsNumberGreatenThan(int studentsNumber) throws Exception;
	
	public Academy saveOrUpdateAcademy(Academy academy) throws Exception;
	
	public Map<String,Boolean> removeAcademy(String codeNumber);
	
	public List<Academy> findAllAcademiesByLocationAndBusinessName(String location, String businessName) throws Exception;
}
