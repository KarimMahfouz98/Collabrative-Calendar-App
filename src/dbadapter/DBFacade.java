package dbadapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import datatypes.AddressData;
import datatypes.DateData;
import datatypes.TimeData;
import interfaces.GMCmds;
import interfaces.IGC;


public class DBFacade implements IGC {
	private static DBFacade instance;

	/**
	 * Constructor which loads the corresponding driver for the chosen database type
	 */
	private DBFacade() {
		try {
			Class.forName("com." + Configuration.getType() + ".jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Implementation of the Singleton pattern.
	 * 
	 * @return
	 */
	public static DBFacade getInstance() {
		if (instance == null) {
			instance = new DBFacade();
		}

		return instance;
	}

	public static void setInstance(DBFacade dbfacade) {
		instance = dbfacade;
	}

	@Override
	public boolean addAppointment(String Name, String Description, AddressData Location, TimeData Duration,
			String Participants, Timestamp SuitableDate,Timestamp Deadline, int GC_ID) {
		boolean checker = false;
		String sqlInsert = "INSERT INTO Appointment (GC_ID,Deadline,DurationHours,DurationMinutes,listOfParticipants,street,city,country,zipCode,Description,name) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		String sqlQuery= "SELECT MAX(ID) FROM Appointment";
		String sqlInsert2= "INSERT INTO listOfDates(participantsCounter,SuitableDate,appointment_ID) values(?,?,?)";
		
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlInsert);
				 PreparedStatement ps2 = connection.prepareStatement(sqlQuery);
				 PreparedStatement ps3 = connection.prepareStatement(sqlInsert2);) {
				ps.setInt(1,GC_ID );
				ps.setTimestamp(2, Deadline);
				ps.setInt(3,Duration.getHour() );
				ps.setInt(4, Duration.getMinute());
				ps.setString(5, Participants);
				ps.setString(6,Location.getStreet());
				ps.setString(7, Location.getCity());
				ps.setString(8, Location.getCountry());
				ps.setInt(9,Location.getZipCode() );
				ps.setString(10,Description );
				ps.setString(11,Name );
							
				ps.executeUpdate();
				
				int lastID = 0;
				try (ResultSet rs = ps2.executeQuery()){
					while(rs.next())
					{
						lastID = rs.getInt(1);
					}
					if(lastID != 0)
					{
						 checker = true;
					}
						
					
				}
				
				ps3.setInt(1,1);
				ps3.setTimestamp(2,SuitableDate);
				ps3.setInt(3,lastID );
				ps3.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	 return checker;	
	}

	public boolean confirmAddedAppointment()
	{
		return true;
	}
	

	@Override
	public ArrayList<Appointment> get_showingAppointment(int GC_ID) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM Appointment where GC_ID = ?";
		ArrayList<Appointment> result = new ArrayList<Appointment> ();

		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(query);)
					{
						ps.setInt(1,GC_ID);
						Appointment a = new Appointment ();
						try (ResultSet res = ps.executeQuery();){
							while(res.next())
							{
								a = new Appointment ();
								a.setID(res.getInt(1));
								a.setGC_ID(res.getInt(2));
								a.setDeadline(res.getTimestamp(3));
								a.setDurationHours(res.getInt(4));
								a.setDurationMinutes(res.getInt(5));
								a.setListOfParticipants(res.getString(6));
								a.setStreet(res.getString(7));
								a.setCity(res.getString(8));
								a.setCountry(res.getString(9));
								a.setZipCode(res.getInt(10));
								a.setDescription(res.getString(11));
								a.setName(res.getString(12));
								if(res.getTimestamp(13) == null) {
									java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf("1111-11-11 11:11:11.1");
									
								
									a.setFinalDate(timestamp);
								}
								else {
								a.setFinalDate(res.getTimestamp(13));
								}
								result.add(a);
							}
						}
						 catch (SQLException e) {
								e.printStackTrace();
							}
					}
				 catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
		}
		return result;
	}
	
	


}
