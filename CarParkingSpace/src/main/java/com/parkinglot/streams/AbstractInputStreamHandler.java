package com.parkinglot.streams;

import java.io.IOException;

import com.parkinglot.InputStreamHandler;
import com.parkinglot.ParkingLotQueryProcessor;
import com.parkinglot.ParkingLotQueryService;
import com.parkinglot.SlotRepository;
import com.parkinglot.commands.Command;
import com.parkinglot.commands.Commands;
import com.parkinglot.impl.ParkingLotQueryProcessorImpl;
import com.parkinglot.impl.ParkingLotQueryServiceImpl;
import com.parkinglot.impl.SlotRepositoryImpl;

public abstract class AbstractInputStreamHandler implements InputStreamHandler {

	private static final String CREATE_PARKING_LOT = "create_parking_lot";

	@Override
	public void handle() {
		SlotRepository slotRepository = null;

		String commandStr = null;
		try {
			commandStr = readLine();
			String[] createCmdParams = commandStr.split(" ");
			if (CREATE_PARKING_LOT.equals(createCmdParams[0])) {
				int slotsCount = Integer.parseInt(createCmdParams[1]);
				slotRepository = SlotRepositoryImpl.getInstance(slotsCount);
				System.out.println("Created a parking lot with " + slotsCount + " slots");
				System.out.println();
			} else {
				throw new IOException("Please enter [" + CREATE_PARKING_LOT + "] command to create parking lot.");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}
		ParkingLotQueryService parkingLotQueryService = new ParkingLotQueryServiceImpl(slotRepository);
		ParkingLotQueryProcessor parkingLotQueryProcessor = new ParkingLotQueryProcessorImpl(parkingLotQueryService);
		Commands commands = new Commands(slotRepository);
		while (true) {
			try {
				commandStr = readLine();
				if (null == commandStr) {
					break;
				}
				String[] params = commandStr.split(" ");
				Command command = commands.getCommand(params[0]);
				if (null != command) {
					command.execute(params);
				} else {
					parkingLotQueryProcessor.process(params);
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			System.out.println();
		}
	}

	public abstract String readLine() throws IOException;

}
