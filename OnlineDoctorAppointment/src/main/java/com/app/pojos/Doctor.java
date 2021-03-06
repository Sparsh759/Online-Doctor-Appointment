package com.app.pojos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Doctor extends Person {
	@Column(length = 20)
	@NonNull
	private String registrationNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NonNull
	private Date registrationDate;
	@Column(length = 30)
	@NonNull
	private String stateMedicalCouncil;
	@Column(length = 30)
	@NonNull
	private String specialization;
	@Column(length = 30)
	@NonNull
	private String qualification;
	@OneToOne(mappedBy = "doctorId" , cascade = CascadeType.ALL)
	@JsonManagedReference(value="doctor")
	private Address address;
	@NonNull
	private Integer fees;
	private boolean isNew=true;
	
	@OneToMany(mappedBy="drId", cascade = CascadeType.ALL)
	private List<TimeFrame> timeSlots = new ArrayList<TimeFrame>();
	
	private boolean isVerified;
	
	@OneToMany(mappedBy="drId")
	private List<Appointment> appointment;

	public Doctor() {
		super();
	}

	public Doctor(Integer id, char[] password, String name, Date dob, String email, String phoneNo, String gender,
			String photo, String registrationNo, Date registrationDate, String stateMedicalCouncil,
			String qualification, Address address) {
		super(id, password, name, dob, email, phoneNo, gender, photo);
		this.registrationNo = registrationNo;
		this.registrationDate = registrationDate;
		this.stateMedicalCouncil = stateMedicalCouncil;
		this.qualification = qualification;
		this.address = address;
	}
	
	
	
	public Doctor(Integer id, char[] password, String name, Date dob, String email, String phoneNo, String gender,
			String photo, String registrationNo, Date registrationDate, String stateMedicalCouncil,
			String specialization, String qualification, Address address, Integer fees, boolean isNew,
			List<TimeFrame> timeSlots, boolean isVerified, List<Appointment> appointment) {
		super(id, password, name, dob, email, phoneNo, gender, photo);
		this.registrationNo = registrationNo;
		this.registrationDate = registrationDate;
		this.stateMedicalCouncil = stateMedicalCouncil;
		this.specialization = specialization;
		this.qualification = qualification;
		this.address = address;
		this.fees = fees;
		this.isNew = isNew;
		this.timeSlots = timeSlots;
		this.isVerified = isVerified;
		this.appointment = appointment;
	}

	public Doctor(char[] password, String name, Date dob, String email, String phoneNo, String gender,
			String registrationNo, Date registrationDate, String stateMedicalCouncil, String specialization,
			String qualification, Address address, Integer fees) {
		super(password, name, dob, email, phoneNo, gender);
		this.registrationNo = registrationNo;
		this.registrationDate = registrationDate;
		this.stateMedicalCouncil = stateMedicalCouncil;
		this.specialization = specialization;
		this.qualification = qualification;
		this.address = address;
		this.fees = fees;
	}

	public Doctor(char[] password, String name, Date dob, String email, String phoneNo, String gender, String photo,
			String registrationNo, Date registrationDate, String stateMedicalCouncil, String specialization,
			String qualification) {
		super(password, name, dob, email, phoneNo, gender, photo);
		this.registrationNo = registrationNo;
		this.registrationDate = registrationDate;
		this.stateMedicalCouncil = stateMedicalCouncil;
		this.specialization = specialization;
		this.qualification = qualification;
	}

	public Doctor(char[] password, String name, Date dob, String email, String phoneNo, String gender,
			String registrationNo, Date registrationDate, String stateMedicalCouncil, String specialization,
			String qualification, boolean isVerified) {
		super(password, name, dob, email, phoneNo, gender);
		this.registrationNo = registrationNo;
		this.registrationDate = registrationDate;
		this.stateMedicalCouncil = stateMedicalCouncil;
		this.specialization = specialization;
		this.qualification = qualification;
		this.isVerified = isVerified;
	}
	

	public Doctor(Integer id, String name, String specialization, Integer fees) {
		super(id, name);
		this.specialization = specialization;
		this.fees = fees;
	}

	public Doctor(Integer id) {
		super(id);
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getStateMedicalCouncil() {
		return stateMedicalCouncil;
	}

	public void setStateMedicalCouncil(String stateMedicalCouncil) {
		this.stateMedicalCouncil = stateMedicalCouncil;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		address.setDoctorId(this);
		this.address = address;
	}
	

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	
	 public Integer getFees() {
		return fees;
	}

	public void setFees(Integer fees) {
		this.fees = fees;
	}
	
	
	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public List<TimeFrame> getTimeSlots() { return timeSlots; }
	  
	  public void setTimeSlots(List<TimeFrame> timeSlots) { this.timeSlots =
	  timeSlots; }
	 
	 

	public boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	
	/*
	 * public List<Appointment> getAppointment() { return appointment; }
	 * 
	 * public void setAppointment(List<Appointment> appointment) { this.appointment
	 * = appointment; }
	 */

	
		
		  public void setTimeSlot( TimeFrame timeFrame) {
		  
		  timeSlots.add(timeFrame); timeFrame.setDrId(this);
		  
		  }
		 
	 
	
	@Override
	public String toString() {
		return "Doctor [registrationNo=" + registrationNo + ", registrationDate=" + registrationDate
				+ ", stateMedicalCouncil=" + stateMedicalCouncil + ", Qualification=" + qualification + ", address="
				+ address + ", getId()=" + getId() + ", getPassword()=" + Arrays.toString(getPassword())
				+ ", getName()=" + getName() + ", getDob()=" + getDob() + ", getEmail()=" + getEmail()
				+ ", getPhoneNo()=" + getPhoneNo() + ", getGender()=" + getGender() +  "]";
	}

	
}
