package com.oocl.cultivation;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

//    @Override
//    public Ticket park(Car car) throws NotEnoughSpaceException {
//        for (ParkingLot parkingLot : parkingLotList) {
//            if (parkingLot.isHasAvailableSlot()) {
//                return parkingLot.park(car);
//            }
//        }
//        throw new NotEnoughSpaceException("Not Enough Space");
//    }
}
