package com.walmart;

import java.util.ArrayList;
import java.util.List;

import com.walmart.models.Request;
import com.walmart.models.Response;
import com.walmart.models.SafetyRiskFactor;
import com.walmart.services.MovieTheater;
import com.walmart.tests.AppTest;
import com.walmart.utilities.FileOperations;

public class MovieTheaterApp {

	public static void main(String[] args) {
		if (args.length > 0) {
			
			// reading file
			FileOperations fileOperations = new FileOperations();
			//takes the file path as input and parse each line separately, each line will be separate request 
			//split each line of input -request id and no of seats and generate it as request object
			//contains list of all requests r1- 5, r2-3
			List<Request> requests = fileOperations.readFileLines(args[0]);
			
			// 10 X 20 seats as given in the problem statement
			// Creating Theater 
			// rows, columns, safety factor, buffer
			MovieTheater firstmovieTheater = new MovieTheater(10, 20, SafetyRiskFactor.HIGH, 3);
			
			// output response
			List<Response> output = new ArrayList<>();
			
			// trying to book
			requests.forEach(request -> {
				output.add(firstmovieTheater.bookSeat(request));
			});
			
			// writing into output file
			fileOperations.writeIntoFile("output.txt", output);
			
			// executing the testcases
			AppTest apptest = new AppTest();
			apptest.testCases();
			
		}
	}

}
