package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import application.CAApplication;
import datatypes.AddressData;
import datatypes.TimeData;
import dbadapter.Appointment;


public class GMGUI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet is responsible for asking the user to add Appointment Info & Add GC Info for viewing
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");
		if (request.getParameter("action").equals("add"))
		{
		try {
			request.setAttribute("pagetitle", "Add Appointment");
			request.getRequestDispatcher("/templates/add_appointment.ftl").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		if (request.getParameter("action").equals("view"))
			{
			try {
				request.setAttribute("pagetitle", "Enter Group Calendar ID");
				request.getRequestDispatcher("/templates/enter_GC_ID.ftl").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
	}
	

	
	

protected void doPost(HttpServletRequest request, HttpServletResponse response) {

	if (request.getParameter("action").equals("accessGC"))
	{
	try {
		int ID = Integer.parseInt(request.getParameter("gcid"));
		request.setAttribute("pagetitle", "View Appointment");
		
		ArrayList<Appointment> app = CAApplication.getInstance().acessGroupCalendar(ID);
		for(int i=0; i<app.size(); i++) {
			
			System.out.println(app.get(i).getName());
			System.out.println(app.get(i).getDeadline());
			System.out.println(app.get(i).getListOfParticipants());
			
		}
		request.setAttribute("list", app);
		request.getRequestDispatcher("/templates/view_appointments.ftl").forward(request, response);
	} catch (Exception e) {
		e.printStackTrace();
	
	}
	}
	else if (request.getParameter("action").equals("add")) {
	String name = (String) request.getParameter("name");
	String description = (String) request.getParameter("description");
	String street = (String) request.getParameter("street");
	String city= (String) request.getParameter("city");
	String country = (String) request.getParameter("country");
	int zipCode =  Integer.parseInt(request.getParameter("zipCode"));
	String suitableDate= (String)request.getParameter("suitableDate");
	int durationHours = Integer.parseInt(request.getParameter("hours"));
	int durationMinutes = Integer.parseInt(request.getParameter("minutes"));
	String participants = (String) request.getParameter("participants");
	String Deadline = (String) request.getParameter("Deadline");
	int GC_ID = Integer.parseInt(request.getParameter("GC_ID"));
	
	try {
	AddressData Location = new AddressData();
	Location.setStreet(street);
	Location.setCity(city);
	Location.setCountry(country);
	Location.setZipCode(zipCode);
	TimeData Duration= new TimeData();
	Duration.setHour(durationHours);
	Duration.setMinute(durationMinutes);
	
	
	DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
	Date date = dateformat.parse(Deadline);
	long time = date.getTime();
	Timestamp deadline = new Timestamp(time);
	
	date = dateformat.parse(suitableDate);
	time = date.getTime();
	Timestamp suitable_date = new Timestamp(time);
	
	
	
	System.out.println(suitable_date);
	System.out.println(deadline);
	
	int res = new CAApplication().makeAppointment(name, description, Location,Duration, participants,suitable_date,deadline,GC_ID);
	if(res == 1)
	{
		try {
			request.setAttribute("pagetitle", "Add Appointment");
			request.setAttribute("message", "New appointment successfully stored in the database.");
			request.getRequestDispatcher("/templates/showConfirmMake.ftl").forward(request, response);

		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	

	}
}

}

	
