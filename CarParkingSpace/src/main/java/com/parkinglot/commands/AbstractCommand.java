package com.parkinglot.commands;

import com.parkinglot.SlotRepository;

public abstract class AbstractCommand implements Command {

	protected SlotRepository slotRepository;

	public AbstractCommand(SlotRepository slotRepository) {
		this.slotRepository = slotRepository;
	}

}
