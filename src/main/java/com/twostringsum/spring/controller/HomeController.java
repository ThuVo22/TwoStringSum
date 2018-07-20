package com.twostringsum.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twostringsum.spring.ResultObject;
import com.twostringsum.spring.SumOfTwoLargeNumbersApplication;
import com.twostringsum.spring.bus.SumTwoString;
import com.twostringsum.spring.implement.HandleINotifier;
/**
 * @author ThuVo
 */
@Controller
public class HomeController {

	private SumTwoString sumProvider;
	private String sessionNumberA = "sessionNumberA";
	private String sessionNumberB = "sessionNumberB";
	private String sessionResultObject = "sessionResultObject";
	private HandleINotifier notifier;

	@Autowired
	SumOfTwoLargeNumbersApplication sumTwoNumberApp;

	@GetMapping("/")
	public String index() {

		return "index";
	}

	// Method is called from the client with post method. 
	// As well as when the users press submit button
	@PostMapping("/")
	public @ResponseBody ResultObject index(HttpServletRequest request, String numberA, String numberB) {

		// Submit has the same function as reseting the calculation
		// We save the values of session with the key, which is the value of the variables.
		// sessionNumberA and sessionNumberB
		request.getSession().setAttribute(sessionNumberA, numberA);
		request.getSession().setAttribute(sessionNumberB, numberB);

		/*
		 * After saving the new values for numberA and numberB session,
		 * we originate the new ResultObject.
		 * ResultObject is the Object that stores all the data
		 * that we will response back to the client
		 */
		// Reinitialize the whole object
		ResultObject resultObject = new ResultObject();

		// Set the reset object into notifier
		
		/*
		 * We create new instance for HangleNotifier and assign it to the notifier
		 * variable.
		 * HandleINotifier is the class that implements INotifier interface.
		 * The main task of it is to override the send method of INotifier.
		 * Assign the value of the parameters that have been sent from the 
		 * send function.
		 */
		
		notifier = new HandleINotifier(resultObject);
		
		/*
		 * Declare an instance of SumTwoString and send them to 2 parameters
		 * which are notifier and resultObject.
		 * Initialize new instance of SumTwoString with two parameters which are
		 * initialized above into sumProvider.
		 */
		sumProvider = new SumTwoString(notifier, resultObject);

		// Call the findSum method to execute the calculation
		sumProvider.findSum(numberA, numberB);

		 // Save resultObject in session so we can use it for the next request
		request.getSession().setAttribute(sessionResultObject, resultObject);

		return resultObject;
	}

	@PostMapping("/next")
	public @ResponseBody ResultObject nextStep(HttpServletRequest request) {

		ResultObject resultObject = (ResultObject) request.getSession().getAttribute(sessionResultObject);

		resultObject.setIndex(resultObject.getIndex() + 1);

		String numberA = (String) request.getSession().getAttribute(sessionNumberA);
		String numberB = (String) request.getSession().getAttribute(sessionNumberB);

		notifier = new HandleINotifier(resultObject);

		sumProvider = new SumTwoString(notifier, resultObject);
		sumProvider.findSum(numberA, numberB);
		request.getSession().setAttribute(sessionResultObject, resultObject);

		return resultObject;
	}
}
