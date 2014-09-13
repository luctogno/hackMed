package net.tinvention.server.dataLayer;

import java.net.UnknownHostException;

import net.tinvention.server.model.BaseModel;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public abstract class AbstractMongoDao<T extends BaseModel> {

	private final String connectionString = "mongodb://192.168.205.178:27017/";
	private final String dbNameTinSleep = "TinSleep";

	private final Logger logger = Logger.getLogger(this.getClass());

	protected DBCollection mongoConnect(String collectionName) {
		MongoClient client;

		try {
			client = new MongoClient(connectionString);
			MongoClientURI uri = new MongoClientURI(connectionString);
			client = new MongoClient(uri);
			DB db = client.getDB(dbNameTinSleep);
			DBCollection collection = db.getCollection(collectionName);
			return collection;
		} catch (UnknownHostException e) {
			logger.error("connection failure " + e.getMessage(), e);
			return null;
		}
	}

	protected abstract T fromMongo(DBObject objectDB);

	protected abstract DBObject toMongo(T data);

}
