package com.parkinglot.commands;

import com.parkinglot.Car;
import com.parkinglot.SlotRepository;
import com.parkinglot.exceptions.ParkingLotOverFlowException;
import com.parkinglot.impl.CarImpl;

public class ParkCarCommand extends AbstractCommand {

	public ParkCarCommand(SlotRepository slotRepository) {
		super(slotRepository);
	}

	@Override
	public void execute(String[] args) {
		Car car = new CarImpl();
		car.setRegNum(args[1]);
		car.setColor(args[2]);

		try {
			slotRepository.allotSlot(car);
			System.out.println("Allocated slot number:" + car.getAllotedSlot());
		} catch (ParkingLotOverFlowException e) {
			System.out.println(e.getMessage());
		}

	}

}
