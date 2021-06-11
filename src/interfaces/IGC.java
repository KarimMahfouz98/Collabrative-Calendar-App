package interfaces;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import datatypes.AddressData;
import datatypes.TimeData;
import dbadapter.GroupCalendar;
import dbadapter.Appointment;


public interface IGC {

	
	public boolean addAppointment(String Name,String Description,AddressData Location,TimeData Duration,String Participants,Timestamp SuitableDate,Timestamp Deadline,int GC_ID); 
	
	
	
	public ArrayList<Appointment> get_showingAppointment(int GC_ID);


}
