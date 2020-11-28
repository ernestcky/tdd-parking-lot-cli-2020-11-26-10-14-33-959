package com.oocl.cultivation;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Ticket park(Car car) throws NotEnoughSpaceException {
        return this.parkingLotList.get(0).park(car);
    }

    public Car fetch(Ticket ticket) {
        return this.parkingLotList.get(0).fetch(ticket);
    }
}
