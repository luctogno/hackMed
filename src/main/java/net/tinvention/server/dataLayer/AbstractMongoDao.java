package net.tinvention.server.dataLayer;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import net.tinvention.server.model.BaseModel;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;

public abstract class AbstractMongoDao<T extends BaseModel> {

	private final String connectionString = "mongodb://192.168.205.178:27017/";
	private final String dbNameTinSleep = "TinSleep";

	private final Logger logger = Logger.getLogger(this.getClass());

	protected DBCollection mongoConnect(String collectionName) {
		MongoClient client;

		try {
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

	protected List<DBObject> toMongo(List<T> data) {
		List<DBObject> result = new ArrayList<DBObject>();

		for (T item : data) {
			result.add(toMongo(item));
		}

		return result;
	}

	public void InsertData(List<T> dR) {
		try {
			mongoConnect(getDataRawCollectionName()).insert(toMongo(dR));
		} catch (MongoException e) {
			logger.error("mongo exception - " + e.getMessage(), e);
		}
	}

	public abstract String getDataRawCollectionName();

}
