package apicontroller;

import java.lang.reflect.Method;

import store.MapAPI;

public class APIExecution {

	public static String hashmapExecute(String target, String inputstring) {
		String result = null;
		AbstractExecution obj;
		MapAPI map = new MapAPI();
		obj = (AbstractExecution) map.get(target);
		result = obj.execute(inputstring);
		return result;
	}

	// reflect method
	public static String reflectExecute(String target, String inputString) {
		String tmp = "";
		tmp += String.valueOf(target.charAt(0)).toUpperCase();
		tmp += target.substring(1);
		String targetclass = ("apicontroller." + tmp);
		String result = null;
		try {
			Class<?> c = Class.forName(targetclass);
			Method method = c.getMethod("execute", String.class);
			// create new instance; if not it will fail
			result = (String) method.invoke(c.newInstance(), inputString);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}
}
