package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import apicontroller.APIExecution;
import constants.RespondConstants;

public class Handler extends AbstractHandler {

	@Override
	// target of the target of the request, which is either a URI or a name from a
	// named dispatcher.
	// baseRequest of the Jetty mutable request object, which is always unwrapped.
	// request of the immutable request object, which might have been wrapped.
	// response of the response, which might have been wrapped.
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		baseRequest.setHandled(true);
		// setting servlet
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods",
				"POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.addHeader("Access-Control-Allow-Headers",
				"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		InputStreamReader isr = new InputStreamReader(request.getInputStream());
		BufferedReader reader = new BufferedReader(isr);
		String inputString = reader.readLine();
		String dataRespond = null;
		target = target.substring(1);
		
//		if (validateParameter(target)) {
//			if (target.equals(APIConstants.API_REGISTER)) {
//				Register register = new Register();
//				dataRespond = register.execute(inputString);
//			} else if (target.equals(APIConstants.API_LOGIN)) {
//				Login login = new Login();
//				dataRespond = login.execute(inputString);
//			} else if (target.equals(APIConstants.API_GETINFO)) {
//				Getinfo getinfo = new Getinfo();
//				dataRespond = getinfo.execute(inputString);
//			} else {
//				dataRespond = RespondConstants.ERROR_RESPOND;
//			}
//		} else {
//			dataRespond = RespondConstants.ERROR_RESPOND;
//		}
		
//		if (validateParameter(target)) {
//			dataRespond = APIExecution.reflectExecute(target, inputString);
//		} else {
//			dataRespond = RespondConstants.ERROR_RESPOND;
//		}
		
		if (validateParameter(target)) {
			dataRespond = APIExecution.hashmapExecute(target, inputString);
		} else {
			dataRespond = RespondConstants.ERROR_RESPOND;
		}
		
		System.out.println("input string: " + inputString);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(dataRespond);
		writer.flush();
		writer.close();
	}

	public static boolean validateParameter(String param) {
		return ((param != null) && (!param.equals("")));
	}
	
}
