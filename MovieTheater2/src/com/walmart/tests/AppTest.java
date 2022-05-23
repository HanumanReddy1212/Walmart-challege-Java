package com.walmart.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.walmart.models.Request;
import com.walmart.models.Response;
import com.walmart.models.SafetyRiskFactor;
import com.walmart.services.MovieTheater;

public class AppTest {
	
	MovieTheater firstmovieTheater;
	
	public void testCases() {
		firstmovieTheater = new MovieTheater(10, 20, SafetyRiskFactor.MEDIUM, 3);
		checkFirstComeFirstServe();
		checkWithZeroTickets();
		checkConsecutiveGroup();
		checkLargeGroupConsecutive();
	}

	private void checkLargeGroupConsecutive() {
		Request req = new Request("R004", 22);
		List<String> k = Arrays.asList("J13", "J14", "J15", "J16", "J17", "J18", "J19", "J20", "I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8", "I9", "I10", "I11", "I12", "I13", "I14");
		Response result = firstmovieTheater.bookSeat(req);
		if(result.getStatus().equals("SUCCESS") && result.getSeatId().equals(k)) {
			System.out.println("Consecutive Group Placement(Shift to next row) testcase Passed!");
		}else {
			System.out.println("Consecutive Group Placement(Shift to next row) testcase Failed!");
		}
		
	}

	private void checkConsecutiveGroup() {
		Request req = new Request("R003",3);
		List<String> k = Arrays.asList("J7", "J8", "J9");
		Response result = firstmovieTheater.bookSeat(req);
		if(result.getStatus().equals("SUCCESS") && result.getSeatId().equals(k)) {
			System.out.println("Consecutive Group Placement testcase Passed!");
		}else {
			System.out.println("Consecutive Group Placement testcase Failed!");
		}
		
	}

	private void checkWithZeroTickets() {
		Request req = new Request("R002", 0);
		Response res = new Response("R002", new ArrayList<>() , "SUCCESS");
		Response result = firstmovieTheater.bookSeat(req);
		if(result.getStatus().equals("SUCCESS") && result.getSeatId().equals(res.getSeatId())) {
			System.out.println("Zero tickets testcase Passed!");
		}else {
			System.out.println("Zero tickets testcase Failed!");
		}
		
	}

	private void checkFirstComeFirstServe() {
		Request req = new Request("R001", 3);
		Response res = new Response("R001", new ArrayList<>(Arrays.asList("J1", "J2", "J3")) , "SUCCESS");
		Response result = firstmovieTheater.bookSeat(req);
		if(result.getStatus().equals("SUCCESS") && result.getSeatId().equals(res.getSeatId())) {
			System.out.println("First Come First Serve Passed!");
		}else {
			System.out.println("First Come First Serve Failed!");
		}
	}
	
}
