package net.tinvention.server.dataLayer;

import java.util.ArrayList;
import java.util.List;

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
public class AlertDao extends AbstractMongoDao<Alert> {

	private final String dataRawCollectionName = "alert";
	private final String dataAnalizedCollectionName = "dataAnalized";

	private final Logger logger = Logger.getLogger(this.getClass());

	public Alert GetDataRawForId(ObjectId id) {
		try {
			return fromMongo(mongoConnect(dataRawCollectionName).findOne(id));
		} catch (MongoException e) {
			logger.error("mongo exception - " + e.getMessage(), e);
			return null;
		}
	}

	public List<Alert> GetDataRaw() {
		try {

			DBCursor cursor = mongoConnect(dataRawCollectionName).find();
			List<Alert> toReturn = new ArrayList<Alert>();

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

	public List<Alert> GetDataRaw(int number) {
		try {
			BasicDBObject sort = new BasicDBObject();
			sort.put("timestamp", -1);
			DBCursor cursor = mongoConnect(dataRawCollectionName).find()
					.sort(sort).limit(number);
			List<Alert> toReturn = new ArrayList<Alert>();

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
	protected DBObject toMongo(Alert data) {
		DBObject obj = new BasicDBObject();

		obj.put("_id", data.getId());
		obj.put("description", data.getDescription());
		obj.put("severity", data.getSeverity().name());
		obj.put("timestamp", data.getTimestamp());
		obj.put("title", data.getTitle());

		return obj;
	}

	@Override
	protected Alert fromMongo(DBObject objectDB) {
		Alert alert = new Alert();

		BasicDBObject obj = (BasicDBObject) objectDB;

		alert.setId(obj.getObjectId("_id"));

		String severity = obj.getString("description");
		if (!StringUtils.isEmpty(severity)) {
			alert.setSeverity(Severity.valueOf(severity));
		}
		alert.setTimestamp(obj.getDate("timestamp"));
		alert.setTitle(obj.getString("title"));

		return alert;
	}

	@Override
	public String getDataRawCollectionName() {
		return dataRawCollectionName;
	}
	
}
