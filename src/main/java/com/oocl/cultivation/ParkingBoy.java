package com.oocl.cultivation;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Ticket park(Car car) throws NotEnoughSpaceException {
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.isHasAvailableSlot()) {
                return parkingLot.park(car);
            }
        }
        throw new NotEnoughSpaceException("Not Enough Space");
    }

    public Car fetch(Ticket ticket) {
        Car car;
        for (ParkingLot parkingLot : parkingLotList) {
            if (ticket.getParkingLot() != null && ticket.getParkingLot().equals(parkingLot.toString())) {
                return parkingLot.fetch(ticket);
            }
        }
        return null;
    }
}
