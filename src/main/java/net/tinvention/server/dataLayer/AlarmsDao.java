package net.tinvention.server.dataLayer;

import java.util.ArrayList;
import java.util.List;

import net.tinvention.server.model.Alarms;
import net.tinvention.server.model.Alert;
import net.tinvention.server.model.DataRaw;
import net.tinvention.server.model.EventType;
import net.tinvention.server.model.Severity;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

@Repository
public class AlarmsDao extends AbstractMongoDao<Alarms> {

	private final String dataRawCollectionName = "alarms";

	private final Logger logger = Logger.getLogger(this.getClass());

	public Alarms GetDataRawForId(ObjectId id) {
		try {
			return fromMongo(mongoConnect(dataRawCollectionName).findOne(id));
		} catch (MongoException e) {
			logger.error("mongo exception - " + e.getMessage(), e);
			return null;
		}
	}

	public List<Alarms> GetDataRaw() {
		try {

			DBCursor cursor = mongoConnect(dataRawCollectionName).find();
			List<Alarms> toReturn = new ArrayList<Alarms>();

			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				toReturn.add(fromMongo(obj));
			}

			return toReturn;

		} catch (MongoException e) {
			logger.error("mongo exception - " + e.getMessage(), e);
			return null;
		}
	}

	public List<Alarms> GetDataRaw(int number) {
		try {
			BasicDBObject sort = new BasicDBObject();
			sort.put("timestamp", -1);
			DBCursor cursor = mongoConnect(dataRawCollectionName).find()
					.sort(sort).limit(number);
			List<Alarms> toReturn = new ArrayList<Alarms>();

			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				toReturn.add(fromMongo(obj));
			}

			return toReturn;

		} catch (MongoException e) {
			logger.error("mongo exception - " + e.getMessage());
			return null;
		}
	}

	@Override
	protected DBObject toMongo(Alarms data) {
		DBObject obj = new BasicDBObject();

		obj.put("_id", data.getId());
		obj.put("description", data.getDescription());
		obj.put("title", data.getTitle());
		obj.put("attivo", data.getAttivo());
		return obj;
	}

	@Override
	protected Alarms fromMongo(DBObject objectDB) {
		Alarms alert = new Alarms();

		BasicDBObject obj = (BasicDBObject) objectDB;

		alert.setId(obj.getObjectId("_id"));

		obj.getString("description");
		
		alert.setTitle(obj.getString("title"));
		
		alert.setAttivo(obj.getBoolean("attivo"));

		return alert;
	}

	@Override
	public String getDataRawCollectionName() {
		return dataRawCollectionName;
	}
	
}
