package com.oocl.cultivation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SmartParkingboy extends ParkingBoy {
    public SmartParkingboy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Ticket park(Car car) throws NotEnoughSpaceException {
        ParkingLot parkingLotToBeUsed = Collections.max(this.parkingLotList, Comparator.comparing(c -> c.getRemainingPlace()));

        if (parkingLotToBeUsed.getRemainingPlace() > 0) {
            return parkingLotToBeUsed.park(car);
        }

        throw new NotEnoughSpaceException("Not Enough Space");
    }
}
