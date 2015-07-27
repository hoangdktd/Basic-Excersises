package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import constants.CollectionConstants;
import constants.UserConstants;
import entity.People;

public class PeopleDAO extends AbstractDAO {

	@Override
	public DBCollection getDBCollection() {
		DBCollection dept = db.getCollection(CollectionConstants.DB_COLLECTION);
		return dept;
	}

	private BasicDBObject castBasicDBObjectFromEntity(People people) {
		BasicDBObject result = new BasicDBObject();
		result.append(UserConstants.USER_EMAIL, people.getUseremail());
		result.append(UserConstants.USER_NAME, people.getUsername());
		result.append(UserConstants.USER_AGE, people.getUserage());
		result.append(UserConstants.USER_PASSWORD, people.getUserpassword());
		return result;
	}

	public People castPeopleFromBasicDBObject(BasicDBObject dbobject) {
		People people = new People();
		people.setUseremail(dbobject.getString(UserConstants.USER_EMAIL));
		people.setUsername(dbobject.getString(UserConstants.USER_NAME));
		people.setUserage(dbobject.getInt(UserConstants.USER_AGE));
		people.setUserpassword(dbobject.getString(UserConstants.USER_PASSWORD));
		return people;
	}

	private DBObject findDBObject(String key, Object value) {
		BasicDBObjectBuilder whereBuilder = BasicDBObjectBuilder.start();
		whereBuilder.append(key, value);
		DBObject where = whereBuilder.get();
		return where;
	}

	private DBObject findDBObjectByID(String id) {
		BasicDBObject query = new BasicDBObject();
		ObjectId oid = new ObjectId(id);
		query.put("_id", oid);
		DBObject obj = getDBCollection().findOne(query);
		return obj;
	}

	// get All ID
	public List<String> getAllID() {
		List<String> listID = new ArrayList<>();
		DBCursor cursor = getDBCollection().find();
		while (cursor.hasNext()) {
			listID.add(cursor.next().get("_id").toString());
		}
		return listID;
	}

	public boolean containsID(String id) {
		boolean result = false;
		List<String> allID = getAllID();
		Iterator<String> ite = allID.iterator();
		while (ite.hasNext()) {
			if (id.equals(ite.next())) {
				result = true;
				break;
			} else {
				result = false;
			}
		}
		return result;
	}

	public boolean containsEmail(String email) {
		boolean result = false;
		List<People> listpeople = getAllPeople();
		Iterator<People> ite = listpeople.iterator();
		while (ite.hasNext()) {
			if (email.equals(ite.next().getUseremail())) {
				result = true;
				break;
			} else {
				result = false;
			}
		}
		return result;
	}

	// get ID by name: name -> dbobject -> ID
	public List<String> getIDByName(String name) {
		List<String> listID = new ArrayList<>();
		DBCursor cursor = getDBCollection().find(
				findDBObject(UserConstants.USER_NAME, name));
		while (cursor.hasNext()) {
			listID.add(cursor.next().get("_id").toString());
		}
		return listID;
	}

	// get all people
	public List<People> getAllPeople() {
		List<People> listpeople = new ArrayList<>();
		DBCursor cursor = getDBCollection().find();
		while (cursor.hasNext()) {
			listpeople.add(castPeopleFromBasicDBObject((BasicDBObject) cursor
					.next()));
		}
		return listpeople;
	}

	// get people by name
	public List<People> getPeopleByName(String name) {
		List<People> listpeople = new ArrayList<>();
		DBCursor cursor = getDBCollection().find(
				findDBObject(UserConstants.USER_NAME, name));
		while (cursor.hasNext()) {
			listpeople.add(castPeopleFromBasicDBObject((BasicDBObject) cursor
					.next()));
		}
		return listpeople;
	}

	public People getPeopleByEmail(String email) {
		People result = new People();
		if (containsEmail(email)) {
			result = castPeopleFromBasicDBObject((BasicDBObject) getDBCollection()
					.findOne(findDBObject(UserConstants.USER_EMAIL, email)));
		} else
			result = null;
		return result;
	}

	public People getPeopleByID(String id) {
		if (containsID(id)) {
			return castPeopleFromBasicDBObject((BasicDBObject) findDBObjectByID(id));
		} else
			return null;
	}

	public void insertPeople(People people) {
		if (!containsEmail(people.getUseremail())) {
			getDBCollection().insert(castBasicDBObjectFromEntity(people));
		}
	}

	public void updatePeopleByID(String id, People newPeople) {
		if (containsID(id))
			getDBCollection().update(findDBObjectByID(id),
					castBasicDBObjectFromEntity(newPeople));
	}

	public void updateNamebyID(String id, String name) {
		if (containsID(id)) {
			BasicDBObject dbobj = new BasicDBObject();
			dbobj.append("$set",
					new BasicDBObject().append(UserConstants.USER_NAME, name));
			getDBCollection().update(findDBObjectByID(id), dbobj);
		}
	}

	public void updateAgeByID(String id, int age) {
		if (containsID(id)) {
			BasicDBObject dbobj = new BasicDBObject();
			dbobj.append("$set",
					new BasicDBObject().append(UserConstants.USER_AGE, age));
			getDBCollection().update(findDBObjectByID(id), dbobj);
		}
	}

	public void deletePeopleByID(String id) {
		if (containsID(id)) {
			getDBCollection().remove(
					castBasicDBObjectFromEntity(getPeopleByID(id)));
		}
	}

	public void deletePeopleByName(String name) {
		Iterator<People> ite = getPeopleByName(name).iterator();
		while (ite.hasNext()) {
			getDBCollection().remove(castBasicDBObjectFromEntity(ite.next()));
		}
	}

	// getalldocument just use for test demo
	// in public version, it will be remove
	public List<DBObject> getAllDocument() {
		List<DBObject> listDocument = getDBCollection().find().toArray();
		return listDocument;
	}
}
