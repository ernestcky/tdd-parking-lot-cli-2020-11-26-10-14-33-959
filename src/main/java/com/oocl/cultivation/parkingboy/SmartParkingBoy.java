package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.strategy.ParkingStrategy;
import com.oocl.cultivation.strategy.SmartParkingStrategy;
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
    private ParkingStrategy parkingStrategy = new SmartParkingStrategy();

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Ticket park(Car car) throws NotEnoughSpaceException {
        return this.parkingStrategy.park(car, this.parkingLotList);
    }

    public Car fetch(Ticket ticket) throws CarNotFoundException {
        for (ParkingLot parkingLot : this.parkingLotList) {
            if (ticket.getParkingLot() != null && ticket.getParkingLot().equals(parkingLot.getParkingLotName())) {
                return parkingLot.fetch(ticket);
            }
        }
        throw new CarNotFoundException("Car Not Found");
    }
}
