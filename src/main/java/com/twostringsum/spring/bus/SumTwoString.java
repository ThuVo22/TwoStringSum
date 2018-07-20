package com.twostringsum.spring.bus;

import com.twostringsum.spring.ResultObject;
import com.twostringsum.spring.abtract.INotifier;

/**
 * This program is used to find to total of two strings as integers. It will
 * prompt the users two strings as integers and then find the addition of them
 * in the order from right to left.
 * 
 * @author Thu Vo.
 */
public class SumTwoString {

	private INotifier nontifier;
	// Index of the calculating digit
	private int index = 0;
	// carry after the calculation
	private int carry = 0;
	// the last index of the calculation
	private int endIndex;
	// the last result
	private String lastResult = "";
	/* 	To figure out if we need to calculate each step
	 *	If isStep = true; calculate since the step 1,
	 *	If isStep = false; method findSum will start from 0
	 *	Finish when the index = the biggest length, either numberA or numberB
	 */
	private boolean isStep = false;

	/**
	 * 
	 * @param nontifier
	 *            class implements INontifier interface, Determine what the send
	 *            function will do
	 * @param lastResultObject
	 *            ResultObject which contains info of ResultObject from previous
	 *            times calculation
	 */
	public SumTwoString(INotifier nontifier, ResultObject lastResultObject) {
		this.nontifier = nontifier;
		if (lastResultObject != null) {
			this.index = lastResultObject.getIndex();
			this.carry = lastResultObject.getCarry();
			this.lastResult = lastResultObject.getResult();
			this.endIndex = index + 1;
			this.isStep = true;
		}
	}

	public String findSum(final String numberA, final String numberB) {

		int a = 0;
		int b = 0;
		int carry = this.carry;
		int indexA = 0;
		int indexB = 0;
		int sum = 0;
		int max = numberA.length() > numberB.length() ? numberA.length() : numberB.length();
		double percentage = 0.0;
		// Assign the previous result to this result
		// The result of this calculation
		String result = this.lastResult;
		/*	If the request is not implemented by each step, we will
		 *	calculate it until the end of the process.
		 */
		if (!isStep) {
			endIndex = max;
		}
		for (int i = 0; i < max; i++) {

			i = index;
			a = b = 0;
			indexA = numberA.length() - i - 1;
			indexB = numberB.length() - i - 1;

			if (indexA >= 0) {
				a = numberA.charAt(indexA) - '0';
			}

			if (indexB >= 0) {
				b = numberB.charAt(indexB) - '0';
			}

			sum = a + b + carry;
			carry = sum / 10;
			result = sum % 10 + result;
			this.lastResult = result;
			// Assign the value for the process of the work has been done
			percentage = (double) Math.round((i + 1) * 10000 / max) / 100;
			
			//	Send the calculated data during the calculation process
			nontifier.send(result, carry, percentage, i, sum, sum - a - b);
			// Finish the loop when the value of i has reached to then endIndex
			if (i == endIndex - 1) {
				break;
			}
		}

		if (carry > 0) {
			result = carry + result;
		}

		return result == "" ? "0" : result;
	}
}
