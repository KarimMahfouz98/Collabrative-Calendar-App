package datatypes;


public class TimeData {
	private int minute;
	private int hour;
	
	public TimeData() {
	}
	public TimeData(int minute, int hour) {
		super();
		this.minute = minute;
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}

	
}
