package com.sistemi.informativi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Academy implements Serializable{

	@Id
	@Column(name="code_number")
	@Size(min=5, max=5, message="Code number must be exactly 5 chars long")
	private String codeNumber;
	
	private String title;
	
	@Column(name="business_name", length=50)
	private String businessName;
	
	private String location;
	
	@Column(name="students_number")
	private int studentsNumber;
	
	protected Academy() {}

	public Academy(String codeNumber, String title, String businessName, String location, int studentsNumber) {
		super();
		this.codeNumber = codeNumber;
		this.title = title;
		this.businessName = businessName;
		this.location = location;
		this.studentsNumber = studentsNumber;
	}

	/**
	 * @return the codeNumber
	 */
	public String getCodeNumber() {
		return codeNumber;
	}

	/**
	 * @param codeNumber the codeNumber to set
	 */
	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the businessName
	 */
	public String getBusinessName() {
		return businessName;
	}

	/**
	 * @param businessName the businessName to set
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the studentsNumber
	 */
	public int getStudentsNumber() {
		return studentsNumber;
	}

	/**
	 * @param studentsNumber the studentsNumber to set
	 */
	public void setStudentsNumber(int studentsNumber) {
		this.studentsNumber = studentsNumber;
	}
	
	
}
