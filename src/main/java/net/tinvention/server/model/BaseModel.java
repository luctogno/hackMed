package net.tinvention.server.model;

import org.bson.types.ObjectId;

public abstract class BaseModel {

	private ObjectId id;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

}
