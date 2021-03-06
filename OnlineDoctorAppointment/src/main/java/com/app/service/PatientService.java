package com.app.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.PatientRepository;
import com.app.emailService.EmailService;
import com.app.pojos.Patient;
	
@Service
public class PatientService implements IPatientService {

	@Autowired
	PatientRepository dao;
	@Autowired
	private EmailService notificationService;

	@Override
	public boolean addPatient(Patient patient) {
		try {
			dao.save(patient);
		} catch (Exception e) {

			return false;
		}
		notificationService.sendEmail(patient.getEmail(),notificationService.registerSubject(),notificationService.registerBody());

		return true;
	}

	@Override
	public Patient getPatientDetails(String email, char[] password) {

		Optional<Patient> patient = dao.findByEmailAndPassword(email, password);
		if (patient.isPresent()) {
			return patient.get();
		}

		return null;
	}

	/*
	 * @Override public List<Appointment> getAppointmentDetails(Integer patient_id)
	 * {
	 * 
	 * return dao.findAllAppointments(patient_id); }
	 */

	@Override
	public List<String> getPrescription(Integer patient_id) {

		File f = new File("D:/prescription/" + patient_id);

		List<String> prescriptions = new ArrayList<String>();
		
		String[] pathnames = f.list();
		prescriptions=Arrays.asList(pathnames);
		/*
		 * Patient patient = new Patient(patient_id);
		 * 
		 * for (String pathname : pathnames) { prescriptions.add(new
		 * Prescription(patient, pathname)); System.out.println(pathname); }
		 */
		
		return prescriptions;

	}

	@Override
	public boolean addProfileImage(MultipartFile profile, Integer patient_id) {
		
		/*
		 * File dir = new File(
		 * "C:/Users/hp/git/Online-Doctor-Appointment/FrontEnd/OnlineDoctorAppointment/src/assets/images/PatientProfilePic/"
		 * + patient_id);
		 */
		
		
		  File dir = new File("D:/CDAC/Project/Online-Doctor-Appointment/FrontEnd/OnlineDoctorAppointment/src/assets/images/PatientProfilePic/"+ patient_id);
		 
		if (!dir.exists()) {
			dir.mkdir();
		}

		try {
			
			/*
			 * FileOutputStream file = new FileOutputStream( new File(
			 * "C:/Users/hp/git/Online-Doctor-Appointment/FrontEnd/OnlineDoctorAppointment/src/assets/images/PatientProfilePic/"
			 * + patient_id + "/" + profile.getOriginalFilename()));
			 */
			
			  FileOutputStream file = new FileOutputStream( new File(
			  "D:/CDAC/Project/Online-Doctor-Appointment/FrontEnd/OnlineDoctorAppointment/src/assets/images/PatientProfilePic/"
			  + patient_id + "/" + profile.getOriginalFilename()));
			 
			byte[] profilepic = profile.getBytes();
			file.write(profilepic);
			file.close();

			return true;

		} catch (IOException e) {

			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Patient getPatient(Integer id) {
		
		return dao.findById(id).get();
	}

	/*
	 * @Override public void removeAppointment(Integer appointment_id) {
	 * 
	 * dao.cancelAppointment(appointment_id); }
	 * 
	 * @Override public String addAppointment(Integer paient_id, Appointment
	 * appointment) {
	 * 
	 * return dao.addAppointments(drId, patientId, id, status);; }
	 */

}
