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
public class GMViewTest {

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
	public void testViewAppointments() {
		// Start testing for view_appointments
		tester.beginAt("view_appointments?action=view");

		// Check all components of the search form
		tester.assertTitleEquals("CalendarApp - Enter Group Calendar ID");
		tester.assertFormPresent();
		tester.assertTextPresent("Required Information");
		tester.assertTextPresent("Enter Group Calendar ID");
		tester.assertFormElementPresent("gcid");
		tester.assertButtonPresent("submitID");

		// Submit the form with given parameters
		tester.setTextField("gcid", "1");
		

		tester.clickButton("submitID");

		// Check the representation of the table for an empty result
		tester.assertTablePresent("viewAppointments");
	
	
	}

}
