package com.oocl.cultivation.strategy;

import com.oocl.cultivation.exception.NotEnoughSpaceException;
import com.oocl.cultivation.utilities.Car;
import com.oocl.cultivation.utilities.ParkingLot;
import com.oocl.cultivation.utilities.Ticket;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingStrategy implements ParkingStrategy {
    @Override
    public Ticket park(Car car, List<ParkingLot> parkingLotList) throws NotEnoughSpaceException {
        ParkingLot parkingLotToBeUsed = Collections.max(parkingLotList, Comparator.comparing(parkingLot -> parkingLot.computeAvailableRate(parkingLot.getRemainingPlace(), parkingLot.getCapacity())));

        if (parkingLotToBeUsed.isHasAvailableSlot()) {
            return parkingLotToBeUsed.park(car);
        }

        throw new NotEnoughSpaceException("Not Enough Space");
    }
}
