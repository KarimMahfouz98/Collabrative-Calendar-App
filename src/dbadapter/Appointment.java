package dbadapter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;

import datatypes.AddressData;
import datatypes.TimeData;


public class Appointment {

	private int ID ;
	private int GC_ID;
	private Date Deadline;
	private int DurationHours;
	private int DurationMinutes;
	private String listOfParticipants;
	private String street;
	private String city;
	private String country;
	private int zipCode;
	private String Description;
	private String name;
	private Date FinalDate;
	
	
	
	public Appointment(int iD, int gC_ID, Date deadline, int durationHours, int durationMinutes,
			String listOfParticipants, String street, String city, String country, int zipCode, String description,
			String name, Date finalDate) {
		super();
		ID = iD;
		GC_ID = gC_ID;
		Deadline = deadline;
		DurationHours = durationHours;
		DurationMinutes = durationMinutes;
		this.listOfParticipants = listOfParticipants;
		this.street = street;
		this.city = city;
		this.country = country;
		this.zipCode = zipCode;
		Description = description;
		this.name = name;
		FinalDate = finalDate;
	}
	
	public Appointment() {
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getGC_ID() {
		return GC_ID;
	}
	public void setGC_ID(int gC_ID) {
		GC_ID = gC_ID;
	}
	public Date getDeadline() {
		return Deadline;
	}
	public void setDeadline(Date deadline) {
		Deadline = deadline;
	}
	public int getDurationHours() {
		return DurationHours;
	}
	public void setDurationHours(int durationHours) {
		DurationHours = durationHours;
	}
	public int getDurationMinutes() {
		return DurationMinutes;
	}
	public void setDurationMinutes(int durationMinutes) {
		DurationMinutes = durationMinutes;
	}
	public String getListOfParticipants() {
		return listOfParticipants;
	}
	public void setListOfParticipants(String listOfParticipants) {
		this.listOfParticipants = listOfParticipants;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getFinalDate() {
		return FinalDate;
	}
	public void setFinalDate(Date finalDate) {
		FinalDate = finalDate;
	}

	
  
}
