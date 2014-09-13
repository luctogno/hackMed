package net.tinvention.server.model;

import org.bson.types.ObjectId;

public class DataRaw{

	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public int getMinPressure() {
		return minPressure;
	}
	public void setMinPressure(int minPressure) {
		this.minPressure = minPressure;
	}
	public int getMaxPressure() {
		return maxPressure;
	}
	public void setMaxPressure(int maxPressure) {
		this.maxPressure = maxPressure;
	}
	public double getConduttanza() {
		return conduttanza;
	}
	public void setConduttanza(double conduttanza) {
		this.conduttanza = conduttanza;
	}
	
	private ObjectId id;
	private int minPressure;
	private int maxPressure;
	private double conduttanza; 
}
