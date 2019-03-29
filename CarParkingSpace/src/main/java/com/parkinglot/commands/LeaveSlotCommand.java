package com.parkinglot.commands;

import com.parkinglot.SlotRepository;
import com.parkinglot.exceptions.NoCarFoundException;

public class LeaveSlotCommand extends AbstractCommand {

	public LeaveSlotCommand(SlotRepository slotRepository) {
		super(slotRepository);
	}

	@Override
	public void execute(String[] args) {
		try {
			int slot = Integer.valueOf(args[1]);
			slotRepository.deAllotSlot(slot);
			System.out.println("Slot number " + slot + " is free");
		} catch (NumberFormatException | NoCarFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
