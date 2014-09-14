package net.tinvention.server.model;

import org.bson.types.ObjectId;

public class Alarms extends BaseModel {
	
	private ObjectId id;
	private String title;
	private String description;
	private Boolean attivo;
	
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
	public Boolean getAttivo() {
		return attivo;
	}
	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	
}
