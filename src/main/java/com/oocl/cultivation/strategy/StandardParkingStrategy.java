package com.oocl.cultivation.strategy;

import com.oocl.cultivation.exception.NotEnoughSpaceException;
import com.oocl.cultivation.utilities.Car;
import com.oocl.cultivation.utilities.ParkingLot;
import com.oocl.cultivation.utilities.Ticket;

import java.util.List;

public class StandardParkingStrategy implements ParkingStrategy {

    public Ticket park(Car car, List<ParkingLot> parkingLotList) throws NotEnoughSpaceException {
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.isHasAvailableSlot()) {
                return parkingLot.park(car);
            }
        }
        throw new NotEnoughSpaceException("Not Enough Space");
    }
}