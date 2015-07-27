package apicontroller;

import com.google.gson.Gson;

import dao.PeopleDAO;
import entity.People;

public abstract class AbstractExecution {
	public static PeopleDAO peopledao = new PeopleDAO();

	public static People castPeopleFromJSON(String inputString) {
		People result = new People();
		Gson gson = new Gson();
		result = gson.fromJson(inputString, People.class);
		return result;
	}

	public static String castJSONFromPeople(People people) {
		String result = null;
		Gson gson = new Gson();
		result = gson.toJson(people);
		return result;
	}

	public abstract String execute(String input);
}
