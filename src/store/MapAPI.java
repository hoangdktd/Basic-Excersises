package store;

import java.util.HashMap;
import java.util.Map;
import apicontroller.Getinfo;
import apicontroller.Login;
import apicontroller.Register;
import constants.APIConstants;

public class MapAPI {
	private static Map<String, Object> apiexecute = new HashMap<String, Object>();
	public MapAPI (){
		 apiexecute.put(APIConstants.API_REGISTER, new Register());
		 apiexecute.put(APIConstants.API_LOGIN, new Login());
		 apiexecute.put(APIConstants.API_GETINFO, new Getinfo());
	}
	
	public static Object get(String key){
		return apiexecute.get(key);
	}
	
	public static void add(String key, Object object){
		apiexecute.put(key, object);
	}

}
