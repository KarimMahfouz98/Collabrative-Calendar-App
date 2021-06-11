package testing;

import org.junit.Before;
import org.junit.Test;

import net.sourceforge.jwebunit.junit.WebTester;

/**
 * This class performs a system test on the GMGUI using JWebUnit.
 * 
 * 
 *
 */
public class GMAddTest {

	private WebTester tester;

	/**
	 * Create a new WebTester object that performs the test.
	 */
	@Before
	public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl("http://localhost:8080/CA/");
	}

	@Test
	public void testAddAppointments() {
		// Start testing for view_appointments
		tester.beginAt("add_appointment?action=add");

		// Check all components of the search form
		tester.assertTitleEquals("CalendarApp - Add Appointment");
		tester.assertFormPresent();
		tester.assertTextPresent("Required Information");
		tester.assertTextPresent("Name");
		tester.assertFormElementPresent("name");
		tester.assertTextPresent("Description");
		tester.assertFormElementPresent("description");
		tester.assertTextPresent("Street");
		tester.assertFormElementPresent("street");
		tester.assertTextPresent("City");
		tester.assertFormElementPresent("city");
		tester.assertTextPresent("Country");
		tester.assertFormElementPresent("country");
		tester.assertTextPresent("Zip Code");
		tester.assertFormElementPresent("zipCode");
		tester.assertTextPresent("Duration-Hours");
		tester.assertFormElementPresent("hours");
		tester.assertTextPresent("Duration-Minutes");
		tester.assertFormElementPresent("minutes");
		tester.assertTextPresent("Participants");
		tester.assertFormElementPresent("participants");
		tester.assertTextPresent("Suitable Date");
		tester.assertFormElementPresent("suitableDate");
		tester.assertTextPresent("Deadline");
		tester.assertFormElementPresent("Deadline");
		tester.assertTextPresent("Group Calendar");
		tester.assertFormElementPresent("GC_ID");
		tester.assertButtonPresent("submit");
		

		// Submit the form with given parameters

		tester.setTextField("name" , "appTest");

		tester.setTextField("description" , "bbbtest");

		tester.setTextField("street" , "aasas");

		tester.setTextField("city" , "xcvsdv");

		tester.setTextField("country" , "adda");

		tester.setTextField("zipCode" , "1234");

		tester.setTextField("hours" , "1");

		tester.setTextField("minutes" , "20");

		tester.setTextField("participants" , "ad-abg");

		tester.setTextField("suitableDate" , "01/30/2021");

		tester.setTextField("Deadline" , "01/31/2021");
	
		tester.setTextField("GC_ID" , "1");
		

		tester.clickButton("submit");

		// Check the representation of the table for an empty result
		tester.assertTextPresent("New appointment successfully stored in the database.");
	
	
	}

}
