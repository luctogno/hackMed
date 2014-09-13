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
import com.mongodb.MongoException;

@Repository
public class DataManager {

	private final String connectionString = "mongodb://192.168.205.178:27017/";
	private final String dbNameTinSleep = "TinSleep";
	private final String dataRawCollectionName = "dataRaw";
	private final String dataAnalizedCollectionName = "dataAnalized";

	private final Logger logger = Logger.getLogger(this.getClass());

	private DBCollection mongoConnect(String collectionName)
	{
		MongoClient client;

		try {
			client = new MongoClient(connectionString);
			DB db = client.getDB(dbNameTinSleep);
			DBCollection collection = db.getCollection(collectionName);
			return collection;
		} catch (UnknownHostException e) {
			logger.error("connection failure "+ e.getMessage());
			return null;
		}
	}

	public DataRaw GetDataRawForId(ObjectId id){
		try {
			return fromMongo(mongoConnect(dataRawCollectionName).findOne(id));
		} catch (MongoException e) {
			logger.error("mongo exception - "+ e.getMessage());
			return null;
		}
	}

	public List<DataRaw> GetDataRaw(){
		try {

			DBCursor cursor = mongoConnect(dataRawCollectionName).find();
			List<DataRaw> toReturn = new ArrayList<DataRaw>();

			while( cursor.hasNext() )
			{
				DBObject obj = cursor.next();
				toReturn.add(fromMongo(obj));
			}

			return toReturn;

		} catch (MongoException e) {
			logger.error("mongo exception - "+ e.getMessage());
			return null;
		}
	}

	public void InsertDataRaw(DataRaw dR)
	{
		try {
			mongoConnect(dataRawCollectionName).insert(toMongo(dR));
		} catch (MongoException e) {
			logger.error("mongo exception - "+ e.getMessage());

		}
	}

	private DBObject toMongo(DataRaw data)
	{
		DBObject obj = new BasicDBObject();

		obj.put("id",data.getId());
		obj.put("accX", data.getAccX());
		obj.put("accY", data.getAccY());
		obj.put("accZ", data.getAccZ());
		obj.put("timestamp", data.getTimestamp());
		obj.put("type", data.getType());
		obj.put("value", data.getValue());

		return obj;
	}

	private DataRaw fromMongo(DBObject objectDB)
	{
		DataRaw data = new DataRaw();

		BasicDBObject obj = new BasicDBObject();

		data.setId(obj.getObjectId("id"));
		data.setAccX(obj.getInt("accX"));
		data.setAccY(obj.getInt("accY"));
		data.setAccZ(obj.getInt("accZ"));
		data.setTimestamp(obj.getDouble(""));
		data.setType(EventType.valueOf(obj.getString("type")));
		data.setValue(new Float(obj.getDouble("value")));

		return data;
	}
}
