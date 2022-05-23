package com.walmart.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.walmart.models.Request;
import com.walmart.models.Response;

public class FileOperations {

	public FileOperations() {
		super();
	}
	
	public List<Request> readFileLines(String filePath){
		// Using the given path, we read the content
		
		List<Request> requests = new ArrayList<>();
		
		try {
			// from file to bytes
			FileReader fileReader = new FileReader(filePath);
			// from bytes to inputStream
			BufferedReader buffer = new BufferedReader(fileReader);
			// read line by line
			//read line - sends to next line
			String line = buffer.readLine();
			while(line != null) {
				String[] reqAndSeats = line.split(" ");
				// generate request with request id and no of seats to book  R001 AND 5 
				requests.add(new Request(reqAndSeats[0], Integer.parseInt(reqAndSeats[1])));
				//similar to i++
				line = buffer.readLine();
			}
			buffer.close();
		}catch(FileNotFoundException ex) {
			System.err.println("Input file Not found! error is: "+ ex.getMessage());
		}catch(IOException io) {
			System.err.println("Unable to read properly! error is: "+ io.getMessage());
		}catch(Exception ex) {
			System.err.println("Some exception has occured! error is: "+ ex.getMessage());
		}
		return requests;
	}
	
	public void writeIntoFile(String filePath, List<Response> output) {
		try {
			FileWriter writer = new FileWriter(filePath);
			BufferedWriter bw = new BufferedWriter(writer);
			
			output.forEach(res -> {
				String str = null;
				if(res.getStatus().equals("SUCCESS")) {
					str = res.getBookingId() + " " + res.getSeatId().toString().replace("[", "").replace("]", "").replace(" ", "") + "\n";
				}else if(res.getStatus().equals("FULL")) {
					str = "Number of seats are less than the requested number of seats or Houseful!" + "\n";
				}else {
					str = "Could not process your request" + "\n";
				}
				try {
					bw.write(str);
				} catch (IOException e) {
					System.err.println("Unable to write! error is: "+ e.getMessage());
				}
			});
			bw.close();
		} catch (Exception ex) {
			System.err.println("Unable to close! error is: "+ ex.getMessage());
		}
		System.out.println("Successfully processed all the request. Please check " + filePath + " file");
	}
	
}
