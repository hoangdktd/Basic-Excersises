package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import constants.APIConstants;
import entity.People;

public class UserClient {
	public static String sendRequest(String inputString, String api) {
		URL url;
		HttpURLConnection connection = null;
		String result = " ";
		StringBuilder postData = new StringBuilder();
		try {
			// data to send
			postData.append(inputString);
			String encodedData = postData.toString();
			System.out.println("send data " + encodedData);
			// create connection
			String u = ConnectionAddress.URL_ADDRESS + api;
			url = new URL(u);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setRequestProperty("Content-Length", (new Integer(
					encodedData.length())).toString());
			// send data by byte
			byte[] postDataByte = postData.toString().getBytes("UTF-8");
			try {
				OutputStream out = connection.getOutputStream();
				out.write(postDataByte);
				out.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				result = ex.toString();
			}
			// get data from server
			InputStreamReader isr = new InputStreamReader(
					connection.getInputStream());
			BufferedReader buf = new BufferedReader(isr);
			result = buf.readLine();
		} catch (Exception ex) {
			System.out.println(ex.toString());
			result = ex.toString();
		}
		return result;
	}

	public static String register(People people) {
		String result = "";
		Gson gson = new Gson();
		result = sendRequest(gson.toJson(people),
				APIConstants.API_REGISTER);
		return result;
	}

	public static String login(String email, String pass) {
		String result = " ";
		People loginpeople = new People();
		loginpeople.setUseremail(email);
		loginpeople.setUserpassword(pass);
		Gson gson = new Gson();
		result = sendRequest(gson.toJson(loginpeople), APIConstants.API_LOGIN);
		return result;
	}

	public static String getInfo(String email, String pass) {
		String result = " ";
		People getinfopeople = new People();
		getinfopeople.setUseremail(email);
		getinfopeople.setUserpassword(pass);
		Gson gson = new Gson();
		result = sendRequest(gson.toJson(getinfopeople),
				APIConstants.API_GETINFO);
		return result;
	}

	public static void main(String[] args) {
		People duong = new People("duong.le@ntq-solution.com.vn", "Duong", 25,
				"a");
		People hoang = new People("hoang.nguyen@ntq-solution.com.vn", "Hoang",
				25, "b");
		People hang = new People("hang.hoang@ntq-solution.com.vn", "Hang", 21,
				"c");
		System.out.println(UserClient.register(duong));
		System.out.println(UserClient.register(hoang));
		System.out.println(UserClient.register(hang));
		System.out.println(UserClient.login(duong.getUseremail(),
				duong.getUserpassword()));
		System.out.println(UserClient.getInfo(duong.getUseremail(), duong.getUserpassword()));
		System.out.println(UserClient.getInfo(hoang.getUseremail(), hoang.getUserpassword()));
		System.out.println(UserClient.getInfo(hang.getUseremail(), hang.getUserpassword()));
	}
}
