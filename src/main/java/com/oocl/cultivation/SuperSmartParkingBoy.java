package com.oocl.cultivation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    public Double computeAvailableRate(Integer remainingPlace, Integer capacity) {
        return new Double(remainingPlace) /new Double(capacity);
    }

    @Override
    public Ticket park(Car car) throws NotEnoughSpaceException {
        ParkingLot parkingLotToBeUsed = Collections.max(this.getParkingLotList(), Comparator.comparing(c -> this.computeAvailableRate(c.getRemainingPlace(), c.getCapacity())));

        if (parkingLotToBeUsed.isHasAvailableSlot()) {
            return parkingLotToBeUsed.park(car);
        }

        throw new NotEnoughSpaceException("Not Enough Space");
    }
}
