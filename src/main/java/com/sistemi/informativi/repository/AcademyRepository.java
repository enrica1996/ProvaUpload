package com.sistemi.informativi.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemi.informativi.entity.Academy;

public interface AcademyRepository extends JpaRepository<Academy,String>{

	public List<Academy> findByLocation(String location);
	
	public List<Academy> findByStudentsNumberGreaterThan(int stdentsNumber);
	
	public List<Academy> findByLocationAndBusinessName(String location, String businessName);
}
