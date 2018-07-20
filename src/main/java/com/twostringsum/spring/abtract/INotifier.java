package com.twostringsum.spring.abtract;

public interface INotifier {
	/**
	 * 
	 * @param result result
	 * @param carry carry
	 * @param percentage percentage of the progress
	 * @param index index of the calculated digit
	 * @param sum sum of the 2 calculated string and its carry
	 * @param lastCarry the carry of the last calculation
	 */
	public void send(String result, int carry, double percentage, int index, int sum, int lastCarry);
}
