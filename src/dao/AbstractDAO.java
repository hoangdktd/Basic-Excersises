package dao;

import java.net.UnknownHostException;
import java.util.List;
import constants.CollectionConstants;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public abstract class AbstractDAO {
	private static MongoClient instance;
	protected static DB db;
//	public static DBCollection dept;
	
	static {
		try {
			instance = new MongoClient(CollectionConstants.HOST, CollectionConstants.PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		db = instance.getDB(CollectionConstants.DB_NAME);
	}
	
	public List<String> getDBName(){
		List<String> dbName = instance.getDatabaseNames();
		return dbName;
	}
	
	public abstract DBCollection getDBCollection();
}
