package interfaces;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import datatypes.TimeData;
import datatypes.AddressData;
import dbadapter.GroupCalendar;
import dbadapter.Appointment;


public interface GMCmds {

	public int makeAppointment(String Name,String Description,AddressData Location,TimeData Duration,String Participants,Timestamp SuitableDate,Timestamp Deadline,int GC_ID); 
	
	public ArrayList<Appointment> acessGroupCalendar(int GC_ID);
	
}