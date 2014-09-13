package net.tinvention.server.dataLayer;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tinvention.server.model.DataRaw;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.mongodb.*;

public class DataManager {

	private String connectionString = "mongodb://192.168.205.178:27017/";	
	private String dbNameTinSleep = "TinSleep";
	private String dataRawCollectionName = "dataRaw";
	private String dataAnalizedCollectionName = "dataAnalized";
	
	private Logger logger = Logger.getLogger(this.getClass());
	
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
		obj.put("minPressure",data.getMinPressure());
		obj.put("maxPressure",data.getMaxPressure());
		obj.put("conduttanza",data.getConduttanza());
		
		return obj;
	}
	
	private DataRaw fromMongo(DBObject objectDB)
	{
		DataRaw data = new DataRaw();
		
		BasicDBObject obj = new BasicDBObject();
		
		data.setId(obj.getObjectId("id"));
		data.setMinPressure(obj.getInt("minPressure"));
		data.setMaxPressure(obj.getInt("maxPressure"));
		data.setConduttanza(obj.getDouble("conduttanza"));
		
		return data;
	}
}
