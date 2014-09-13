package net.tinvention.server.model;

public class Event {

	private double timestamp;
	private EventType type;
	private float value;
	private int accX;
	private int accY;
	private int accZ;
	
	public double getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(double timestamp) {
		this.timestamp = timestamp;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public int getAccX() {
		return accX;
	}
	public void setAccX(int accX) {
		this.accX = accX;
	}
	public int getAccY() {
		return accY;
	}
	public void setAccY(int accY) {
		this.accY = accY;
	}
	public int getAccZ() {
		return accZ;
	}
	public void setAccZ(int accZ) {
		this.accZ = accZ;
	}
}