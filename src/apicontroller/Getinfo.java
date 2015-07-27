package apicontroller;

import constants.RespondConstants;
import entity.People;

public class Getinfo extends AbstractExecution {

	@Override
	public String execute(String input) {
		String result = null;
		People people = castPeopleFromJSON(input);
		if (peopledao.containsEmail(people.getUseremail())) {
			if (people.getUserpassword().equals(
					peopledao.getPeopleByEmail(people.getUseremail())
							.getUserpassword())) {
				result = castJSONFromPeople(peopledao.getPeopleByEmail(people
						.getUseremail()));
			} else
				result = RespondConstants.ERROR_RESPOND;

		} else
			result = RespondConstants.ERROR_RESPOND;
		return result;
	}
	
}
