package net.tinvention.server.model;

import java.util.Calendar;
import java.util.Date;

public class Alert extends BaseModel {

	private String title;
	private String description;
	private Severity severity;
	private Date timestamp;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getTimestampYear() {
		if (timestamp == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(timestamp);
		return c.get(Calendar.YEAR);
	}

	public int getTimestampMonth() {
		if (timestamp == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(timestamp);
		return c.get(Calendar.MONTH);
	}

	public int getTimestampDay() {
		if (timestamp == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(timestamp);
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	public long getTimestampMillis()
	{
	return timestamp.getTime();
	}
}
