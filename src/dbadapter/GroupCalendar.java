package dbadapter;

import java.sql.Timestamp;

import datatypes.TimeData;


public class GroupCalendar {

	private int ID;
	private String Description;
	private String Name;
	



	public GroupCalendar(int iD, String description, String name) {
		super();
		ID = iD;
		Description = description;
		Name = name;
	}




	public int getID() {
		return ID;
	}




	public void setID(int iD) {
		ID = iD;
	}




	public String getDescription() {
		return Description;
	}




	public void setDescription(String description) {
		Description = description;
	}




	public String getName() {
		return Name;
	}




	public void setName(String name) {
		Name = name;
	}



}
