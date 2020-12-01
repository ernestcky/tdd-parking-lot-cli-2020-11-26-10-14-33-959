package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.utilities.Car;
import com.oocl.cultivation.utilities.ParkingLot;
import com.oocl.cultivation.utilities.Ticket;
import com.oocl.cultivation.exception.CarNotFoundException;
import com.oocl.cultivation.exception.NotEnoughSpaceException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy  {
    private List<ParkingLot> parkingLotList;
    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Double computeAvailableRate(Integer remainingPlace, Integer capacity) {
        return new Double(remainingPlace) /new Double(capacity);
    }

    public Ticket park(Car car) throws NotEnoughSpaceException {  //TODO: put the function to parkingLot
        ParkingLot parkingLotToBeUsed = Collections.max(this.parkingLotList, Comparator.comparing(c -> this.computeAvailableRate(c.getRemainingPlace(), c.getCapacity())));

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
