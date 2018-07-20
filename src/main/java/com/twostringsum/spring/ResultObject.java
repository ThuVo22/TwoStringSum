package com.twostringsum.spring;

public class ResultObject {
	private String result = "";
	private int carry = 0;
	private double percentage = 0.0;
	private int index = 0;
	private int sum = 0;
	private int lastCarry;
	
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getCarry() {
		return carry;
	}
	public void setCarry(int carr) {
		this.carry = carr;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getLastCarry() {
		return lastCarry;
	}
	public void setLastCarry(int lastCarry) {
		this.lastCarry = lastCarry;
	}
	
	
}
