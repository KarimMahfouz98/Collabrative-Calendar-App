package application;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import datatypes.AddressData;
import datatypes.TimeData;
import dbadapter.GroupCalendar;
import dbadapter.DBFacade;
import dbadapter.Appointment;
import interfaces.GMCmds;


/**
 * This class contains the VRApplication which acts as the interface between all
 * components.
 * 
 * @author swe.uni-due.de
 *
 */
public class CAApplication implements GMCmds {

	private static CAApplication instance;

	/**
	 * Implementation of the Singleton pattern.
	 * 
	 * @return
	 */
	public static CAApplication getInstance() {
		if (instance == null) {
			instance = new CAApplication();
		}

		return instance;
	}

	@Override
	//(String Name,String Description,AddressData Location,TimeData Duration,String Participants,Date SuitableDate,Date Deadline,int GC_ID); 
	public int makeAppointment(String Name, String Description, AddressData Location, TimeData Duration,
			String Participants, Timestamp SuitableDate,Timestamp Deadline,int GC_ID) {
		try {
			boolean result = DBFacade.getInstance().addAppointment(Name, Description, Location, Duration, Participants, SuitableDate,Deadline, GC_ID);
			//(String Name, String Description, AddressData Location, TimeData Duration,
			//String Participants, Date SuitableDate,Date Deadline, int GC_ID)
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public ArrayList<Appointment> acessGroupCalendar(int GC_ID) {
		// TODO Auto-generated method stub
		ArrayList<Appointment> result = new ArrayList<Appointment>();
		try {
			result = DBFacade.getInstance().get_showingAppointment(GC_ID);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}




}
