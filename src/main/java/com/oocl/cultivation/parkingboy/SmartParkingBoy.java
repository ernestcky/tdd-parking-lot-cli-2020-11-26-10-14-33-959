package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.utilities.Car;
import com.oocl.cultivation.utilities.ParkingLot;
import com.oocl.cultivation.utilities.Ticket;
import com.oocl.cultivation.exception.CarNotFoundException;
import com.oocl.cultivation.exception.NotEnoughSpaceException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy {
    private List<ParkingLot> parkingLotList;
    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Ticket park(Car car) throws NotEnoughSpaceException {
        ParkingLot parkingLotToBeUsed = Collections.max(this.parkingLotList, Comparator.comparing(c -> c.getRemainingPlace()));

        if (parkingLotToBeUsed.isHasAvailableSlot()) {
            return parkingLotToBeUsed.park(car);
        }

        throw new NotEnoughSpaceException("Not Enough Space");
    }

    public Car fetch(Ticket ticket) throws CarNotFoundException {
        Car car;
        //todo: add a funciton to get parkinglot name
        for (ParkingLot parkingLot : parkingLotList) {
            if (ticket.getParkingLot() != null && ticket.getParkingLot().equals(parkingLot.toString())) {
                return parkingLot.fetch(ticket);
            }
        }
        throw new CarNotFoundException("Car Not Found");
    }
}
