package testing;

import org.junit.Before;
import org.junit.Test;

import net.sourceforge.jwebunit.junit.WebTester;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dbadapter.Appointment;
import dbadapter.GroupCalendar;
import datatypes.AddressData;
import datatypes.TimeData;
import dbadapter.Configuration;
import dbadapter.DBFacade;


import junit.framework.TestCase;


public class DBFacadeTestDB extends TestCase{
	private Appointment testAP;
	private GroupCalendar testGC;
	@Before
	public void setUp() {

		// objects to be tested
		testAP = new Appointment(10,11,Timestamp.valueOf("2021-02-01 00:00:00"),2,20,"Mahmoud - Abdelrahman - Mohamed","streetfacade","cityfacade","countryfacade",12345,
				"descfacade","AppointmentFacade",Timestamp.valueOf("2021-02-01 00:00:00"));
		testGC = new GroupCalendar(11, "fName", "fName");
		// SQL statements
		String sqlCleanDB = "DROP TABLE IF EXISTS GroupCalendar, Appointment, listOfDates";
		String sqlCreateTableGroupCalendar= "CREATE TABLE GroupCalendar (ID int(11) NOT NULL AUTO_INCREMENT,Name varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,Description varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,PRIMARY KEY (ID));";
		String sqlCreateTableAppointment = "CREATE TABLE  Appointment  ( ID  int(11) NOT NULL AUTO_INCREMENT, GC_ID  int(11) NOT NULL,Deadline  timestamp NOT NULL, DurationHours  int(11) NOT NULL, DurationMinutes  int(11) NOT NULL, listOfParticipants  varchar(255), street  varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, city  varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, country  varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, zipCode  int(11) NOT NULL, Description  varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, name  varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, FinalDate  timestamp NULL DEFAULT NULL,PRIMARY KEY (ID));";
		String sqlCreateTableListOfDates = "CREATE TABLE listOfDates (participantsCounter int(11) NOT NULL,SuitableDate timestamp NOT NULL,appointment_ID int(11) NOT NULL);";
		
		
		
		String sqlInsertAppointemnt = "INSERT INTO Appointment (ID,GC_ID,Deadline,DurationHours,DurationMinutes,listOfParticipants,street,city,country,zipCode,Description,name) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		String sqlInsertGroupCalendar = "INSERT INTO GroupCalendar(ID,Name,Description) values(?,?,?)";
		
		// Perform database updates
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
				psClean.executeUpdate();
			}
			try (PreparedStatement psCreateGC = connection.prepareStatement(sqlCreateTableGroupCalendar)) {
				psCreateGC.executeUpdate();
			}
			try (PreparedStatement psCreateApp = connection.prepareStatement(sqlCreateTableAppointment)) {
				psCreateApp.executeUpdate();
			}
			try (PreparedStatement psCreateListOfDates = connection.prepareStatement(sqlCreateTableListOfDates)) {
				psCreateListOfDates.executeUpdate();
			}
			
			try (PreparedStatement psInsertGC = connection.prepareStatement(sqlInsertGroupCalendar)) {
				psInsertGC.setInt(1, testGC.getID());
				psInsertGC.setString(2, testGC.getName());
				psInsertGC.setString(3, testGC.getDescription());
				psInsertGC.executeUpdate();
			}
			
			try (PreparedStatement psInsertApp= connection.prepareStatement(sqlInsertAppointemnt)) {
				psInsertApp.setInt(1, testAP.getID() );
				psInsertApp.setInt(2, testAP.getGC_ID() );
				psInsertApp.setTimestamp(3, (Timestamp) testAP.getDeadline());
				psInsertApp.setInt(4, testAP.getDurationHours() );
				psInsertApp.setInt(5, testAP.getDurationMinutes() );
				psInsertApp.setString(6, testAP.getListOfParticipants());
				psInsertApp.setString(7,testAP.getStreet());
				psInsertApp.setString(8, testAP.getCity());
				psInsertApp.setString(9, testAP.getCountry());
				psInsertApp.setInt(10,testAP.getZipCode() );
				psInsertApp.setString(11,testAP.getDescription());
				psInsertApp.setString(12,testAP.getName() );
				psInsertApp.executeUpdate();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Testing getShowingAppointments with non-empty results.
	 */
	@Test
	public void testgetShowingAppointments() {

		// Select a group calendar where the appointment added should exist
	
		int CalendarID = 11;

		ArrayList<Appointment> AppList = DBFacade.getInstance().get_showingAppointment(CalendarID);

		// Verify return values
		assertTrue(AppList.size() == 1);
		assertTrue(AppList.get(0).getID() == testAP.getID());
		assertTrue(AppList.get(0).getGC_ID() == testAP.getGC_ID());
		assertTrue(AppList.get(0).getDeadline().equals( testAP.getDeadline()));
		assertTrue(AppList.get(0).getDurationHours() == testAP.getDurationHours());
		assertTrue(AppList.get(0).getDurationMinutes() == testAP.getDurationMinutes());
		System.out.println(AppList.get(0).getListOfParticipants());
		System.out.println(testAP.getListOfParticipants());
		assertTrue(AppList.get(0).getListOfParticipants().equals( testAP.getListOfParticipants()));
		assertTrue(AppList.get(0).getStreet().equals( testAP.getStreet()));
		assertTrue(AppList.get(0).getCity().equals(testAP.getCity()));
		assertTrue(AppList.get(0).getCountry().equals( testAP.getCountry()));
		assertTrue(AppList.get(0).getZipCode() == testAP.getZipCode());
		assertTrue(AppList.get(0).getDescription().equals( testAP.getDescription()));
		assertTrue(AppList.get(0).getName().equals(testAP.getName()));
		// ...

	}

	/**
	 * Testing getShowingAppointments with empty result.
	 */
	@Test
	public void testgetShowingAppointmentEmpty() {

		// Select a Group Calendar ID where no appointments exist
		int CalendarID = 22;

		ArrayList<Appointment> AppList = DBFacade.getInstance().get_showingAppointment(CalendarID);

		// Verify return values

		assertTrue(AppList.size() == 0);

	}

	/**
	 * Rest database
	 */

	@After
	public void tearDown() {

		// SQL statements
		String sqlCleanDB = "DROP TABLE IF EXISTS groupcalendar,appointment,listofdates";

		// Perform database updates
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
				psClean.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
