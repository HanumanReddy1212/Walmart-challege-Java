package com.walmart.models;

public class Seat implements Comparable<Seat>{

	private String seatId;
	private int priority;
	private boolean isBooked = false;
	private boolean IsBlocked = true;
	
	
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public boolean isBooked() {
		return isBooked;
	}
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	public boolean isIsBlocked() {
		return IsBlocked;
	}
	public void setIsBlocked(boolean isBlocked) {
		IsBlocked = isBlocked;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Override
	//20 more priority
	public int compareTo(Seat seat) {
		if(this.priority > seat.getPriority()) {
			return -1;
		}else if(this.priority < seat.getPriority()) {
			return 1;
		}
		return 0;
	}
	
	
}
