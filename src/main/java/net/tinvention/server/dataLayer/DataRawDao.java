package net.tinvention.server.dataLayer;

import java.util.ArrayList;
import java.util.List;

import net.tinvention.server.model.DataRaw;
import net.tinvention.server.model.EventType;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

@Repository
public class DataRawDao extends AbstractMongoDao<DataRaw> {

	private final String dataRawCollectionName = "dataRaw";

	@Override
	public String getDataRawCollectionName() {
		return dataRawCollectionName;
	}
	
	private final Logger logger = Logger.getLogger(this.getClass());

	public DataRaw GetDataRawForId(ObjectId id) {
		try {
			return fromMongo(mongoConnect(dataRawCollectionName).findOne(id));
		} catch (MongoException e) {
			logger.error("mongo exception - " + e.getMessage(), e);
			return null;
		}
	}

	public List<DataRaw> GetDataRaw() {
		try {

			DBCursor cursor = mongoConnect(dataRawCollectionName).find();
			List<DataRaw> toReturn = new ArrayList<DataRaw>();

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

	public List<DataRaw> GetDataRaw(EventType type, int number) {
		try {

			BasicDBObject example = new BasicDBObject();
			BasicDBObject sort = new BasicDBObject();
			example.put("type", type.name());
			sort.put("timestamp", -1);
			DBCursor cursor = mongoConnect(dataRawCollectionName).find(example)
					.sort(sort).limit(number);
			List<DataRaw> toReturn = new ArrayList<DataRaw>();

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
	protected DBObject toMongo(DataRaw data) {
		DBObject obj = new BasicDBObject();

		obj.put("_id", data.getId());
		obj.put("accX", data.getAccX());
		obj.put("accY", data.getAccY());
		obj.put("accZ", data.getAccZ());
		obj.put("timestamp", data.getTimestamp());
		obj.put("type", data.getType().name());
		obj.put("value", data.getValue());

		return obj;
	}

	@Override
	protected DataRaw fromMongo(DBObject objectDB) {
		DataRaw data = new DataRaw();

		BasicDBObject obj = (BasicDBObject) objectDB;

		data.setId(obj.getObjectId("_id"));

		int accX = obj.getInt("accX", Integer.MIN_VALUE);
		if (accX != Integer.MIN_VALUE) {
			data.setAccX(accX);
		}

		int accY = obj.getInt("accY", Integer.MIN_VALUE);
		if (accY != Integer.MIN_VALUE) {
			data.setAccY(accY);
		}

		int accZ = obj.getInt("accZ", Integer.MIN_VALUE);
		if (accZ != Integer.MIN_VALUE) {
			data.setAccZ(accZ);
		}

		data.setTimestamp(obj.getDouble("timestamp"));

		data.setType(EventType.valueOf(obj.getString("type")));

		double value = obj.getDouble("value", Double.MIN_VALUE);
		if (value != Double.MIN_VALUE) {
			data.setValue(new Float(value));
		}

		return data;
	}
}
