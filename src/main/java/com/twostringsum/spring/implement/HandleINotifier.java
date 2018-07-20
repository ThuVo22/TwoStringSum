package com.twostringsum.spring.implement;

import com.twostringsum.spring.ResultObject;
import com.twostringsum.spring.abtract.INotifier;

/**
 * Class implements INotifier interface
 * Override send function of INotifier interface to parse 
 * all parameters of send method to ResultObject
 * @author Admin
 *
 */
public class HandleINotifier implements INotifier {

	// resultObject is used to store the information for the parameters sent by function
	private ResultObject resultObject;

	public void setResultObject(ResultObject resultObject) {
		this.resultObject = resultObject;
	}

	/**
	 *  <p>
	 *  We sent one ResultObject to originate HandleINotifier
	 *  After send method is called, resultObject has created the essential values.
	 *  As wel as we referred to this class and change its data.
	 * @param resultObject	
	 * Parameter stores the information that we received after the calculation.
	 */
	public HandleINotifier(ResultObject resultObject) {
		this.resultObject = resultObject;
	}

	@Override
	public void send(String result, int carry, double percentage, int index, int sum, int lastCarry) {

		this.resultObject.setIndex(index);
		this.resultObject.setPercentage(percentage);
		this.resultObject.setCarry(carry);
		this.resultObject.setResult(result);
		this.resultObject.setSum(sum);
		this.resultObject.setLastCarry(lastCarry);
		
	}

}
