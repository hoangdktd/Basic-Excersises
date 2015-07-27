package constants;

import com.google.gson.Gson;

public class RespondConstants {
	public static final String SUCCESS_RESPOND;
	public static final String ERROR_RESPOND;
	public static final String INFO_RESPOND;
	public static final String REGISTER_RESPOND;
	public static final String REGISTER_FAIL_RESPOND;
	public static final String REGISTER_FAIL_INFO_INCORRECT;
	public static final String LOGIN_RESPOND;
	public static final String LOGIN_FAIL_RESPOND;
	public static final String LOGIN_NO_USERNAME_RESPOND;

	static {
		Gson gson = new Gson();

		Message message;
		SUCCESS_RESPOND = gson.toJson(message = new Message("Success"));

		ERROR_RESPOND = gson.toJson(message = new Message("Fail"));

		INFO_RESPOND = gson.toJson(message = new Message(
				"your infomation: name, age, address"));

		REGISTER_RESPOND = gson.toJson(message = new Message(
				"User register successful"));

		REGISTER_FAIL_RESPOND = gson.toJson(message = new Message(
				"Username already exists, try with another one"));

		LOGIN_RESPOND = gson.toJson(message = new Message(
				"User login successful"));

		LOGIN_NO_USERNAME_RESPOND = gson.toJson(message = new Message(
				"Please register"));

		LOGIN_FAIL_RESPOND = gson.toJson(message = new Message(
				"Incorrect username or password. Please try again"));

		REGISTER_FAIL_INFO_INCORRECT = gson.toJson(message = new Message(
				"Please check username, password and age"));
	}
}

class Message {
	public String message;

	public Message(String message) {
		this.message = message;
	}
}
