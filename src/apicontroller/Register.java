package apicontroller;

import constants.RespondConstants;
import entity.People;

public class Register extends AbstractExecution {

	@Override
	public String execute(String input) {
		String result = null;
		// check email constants
		People people = castPeopleFromJSON(input);
		if (!peopledao.containsEmail(people.getUseremail())) {
			if (people.getUserage() <= 0 || people.getUsername() == null) {
				result = RespondConstants.REGISTER_FAIL_INFO_INCORRECT;
			} else {
				peopledao.insertPeople(people);
				result = RespondConstants.REGISTER_RESPOND;
			}
		} else
			result = RespondConstants.REGISTER_FAIL_RESPOND;
		return result;
	}
}
