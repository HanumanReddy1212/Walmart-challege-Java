package com.walmart.services;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import com.walmart.models.Request;
import com.walmart.models.Response;
import com.walmart.models.SafetyRiskFactor;
import com.walmart.models.Seat;

public class MovieTheater {
	
	private Seat[][] seats;
	private SafetyRiskFactor riskFactor;
	private int buffer;
	private Stack<PriorityQueue<Seat>> availableSeatsInRow = new Stack<PriorityQueue<Seat>>();
	private int totalAvailableSeats = 0;

//	public MovieTheater(SafetyRiskFactor riskFactor, int buffer) {
//		super();
//		this.riskFactor = riskFactor;
//		this.buffer = buffer;
//	}

	public MovieTheater(int rows, int columns, SafetyRiskFactor riskFactor, int buffer) {
		super();
		this.buffer = buffer;
		// char rowSymbol = (char) ('A' + rows-1);
		char rowSymbol =  'A';
		this.seats = new Seat[rows][columns];//initialization
		this.riskFactor = riskFactor;
		
		for(int row = 0; row < rows; row++) {
			//  priority Queue for each row 
			PriorityQueue<Seat> pq = new PriorityQueue<>();
			for(int col = 0; col < columns; col++){
				//creating new seat
				this.seats[row][col] = new Seat();
				// set seat symbol A, seat number from 1 to 20
				this.seats[row][col].setSeatId(rowSymbol + String.valueOf(col+1));
				// assigning priority, A1=20 is more priority
	            this.seats[row][col].setPriority(columns - col);
	            // adding each seat to priority queue
	            pq.add(this.seats[row][col]);
	            this.seats[row][col].setIsBlocked(false);
	            
	        }
			
			if(this.riskFactor.equals(SafetyRiskFactor.HIGH)) {
				//if we want to get last row to picked every time
				//10 rows if risk factor is high - add alternate rows 
				//if rows=4, 0123 add odd row || vice-versa
				if(rows%2 == 0 && row%2 != 0 || rows%2 != 0 && row%2 == 0) {
					this.availableSeatsInRow.push(pq);
					//total seats in all rows
					this.totalAvailableSeats+=pq.size();
				}
			}
			
			else {//if risk factor is not high add all rows 
				this.availableSeatsInRow.push(pq);
				this.totalAvailableSeats+=pq.size();
			}
			//increment after each row
			rowSymbol++;
		}
		
		

	}


	public Seat[][] getSeats() {
		return seats;
	}


	public void setSeats(Seat[][] seats) {
		this.seats = seats;
	}


	public SafetyRiskFactor getRiskFactor() {
		return riskFactor;
	}


	public void setRiskFactor(SafetyRiskFactor riskFactor) {
		this.riskFactor = riskFactor;
	}


	public Response bookSeat(Request request) {
		// response object
										//booking id=R001,       list of seat id we book for a request, status
		Response response = new Response(request.getRequestid(), null, null);
		
		// if no available seats return
		if(totalAvailableSeats < request.getNumberOfSeats()) {
			response.setStatus("FULL");
			return response;
		}
		int i = request.getNumberOfSeats();
		
		List<String> seatIds = new ArrayList<>();
//		O(1)
		PriorityQueue<Seat> topRow = availableSeatsInRow.pop();
//		O(k)
		while(i != 0) {
			//queue is empty and stack is not empty
			if(topRow.size() == 0 && availableSeatsInRow.size() != 0) {
//				O(1) goto next row by pop top element in the stack i.e. empty row
				topRow = availableSeatsInRow.pop();
			}
//			PQ POP- O(log n)
			//A1 A2 A3 A4 A5
			seatIds.add(topRow.poll().getSeatId());
			i--;
			this.totalAvailableSeats--;
		}
		// R001- J1 TO J5
		response.setSeatId(seatIds);
		response.setStatus("SUCCESS");
		//
		if(request.getNumberOfSeats() == 0) {
			//if 0 seats required- since we are popping in line 109 to keep into top row
			availableSeatsInRow.push(topRow);
			return response;
		}
		
		// blocking to right
		int blocking3 = this.buffer;
		// queue size >0 and risk factor is not low 
		while(topRow.size() > 0 && blocking3 != 0 && !this.riskFactor.equals(SafetyRiskFactor.LOW)) {
			//remove seats until 3 
			topRow.poll();
			blocking3--;
			this.totalAvailableSeats--;
		}
		//queue not empty - push it back to stack 
		if(topRow.size() > 0) {
			availableSeatsInRow.push(topRow);
		}
		
		return response;
	}
	
	
	
	
	

}
