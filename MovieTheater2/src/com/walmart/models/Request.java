package com.walmart.models;

public class Request {
	
	private String requestid;
	private int numberOfSeats;
	
	public Request(String requestid, int numberOfSeats) {
		super();
		this.requestid = requestid;
		this.numberOfSeats = numberOfSeats;
	}
	public String getRequestid() {
		return requestid;
	}
	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	
	

}
