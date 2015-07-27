package apicontroller;

import constants.RespondConstants;
import entity.People;

public class Login extends AbstractExecution {

	@Override
	public String execute(String input) {
		String result = null;
		People people = castPeopleFromJSON(input);
		if (peopledao.containsEmail(people.getUseremail())) {
			if (people.getUserpassword().equals(peopledao.getPeopleByEmail(people.getUseremail()).getUserpassword())) {
				result = RespondConstants.LOGIN_RESPOND;
			} else
				result = RespondConstants.LOGIN_FAIL_RESPOND;
		} else
			result = RespondConstants.LOGIN_NO_USERNAME_RESPOND;
		return result;
	}

}
