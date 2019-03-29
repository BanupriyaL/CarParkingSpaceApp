package com.parkinglot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.parkinglot.exceptions.NoCarFoundException;
import com.parkinglot.exceptions.ParkingLotOverFlowException;
import com.parkinglot.streams.CommandLineInputStreamHandler;
import com.parkinglot.streams.FileInputStreamHandler;

public class CarParkingApplication {

	public static void main(String[] args) throws ParkingLotOverFlowException, NoCarFoundException {
		InputStreamHandler inputStreamHandler = null;
		args = new String[] {"D:\\MyProjects\\CarParkingSpace\\src\\test\\file_inputs.txt"};
		if (args.length == 0) {
			inputStreamHandler = new CommandLineInputStreamHandler(System.in);

		} else {
			try {
				FileInputStream inputStream = new FileInputStream(args[0]);
				inputStreamHandler = new FileInputStreamHandler(inputStream);
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
				return;
			}
		}
		inputStreamHandler.handle();
	}
}
