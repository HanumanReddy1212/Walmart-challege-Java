package com.walmart.models;

import java.util.List;

public class Response {
	
	private String bookingId;
	private List<String> seatId;
	private String Status;
	public Response(String bookingId, List<String> seatId, String status) {
		super();
		this.bookingId = bookingId;
		this.seatId = seatId;
		Status = status;
	}
	
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public List<String> getSeatId() {
		return seatId;
	}
	public void setSeatId(List<String> seatId) {
		this.seatId = seatId;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	
	
	

}
