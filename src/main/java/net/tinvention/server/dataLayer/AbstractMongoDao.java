package net.tinvention.server.dataLayer;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import net.tinvention.server.model.DataRaw;
import net.tinvention.server.model.EventType;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;

public abstract class AbstractMongoDao {

	private final String connectionString = "mongodb://192.168.205.178:27017/";
	private final String dbNameTinSleep = "TinSleep";

	private final Logger logger = Logger.getLogger(this.getClass());

	protected DBCollection mongoConnect(String collectionName)
	{
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

}
