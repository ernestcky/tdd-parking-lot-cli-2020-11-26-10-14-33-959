package com.oocl.cultivation.utilities;

public class Ticket {
    // todo: parkingLotname
    private String parkingLot;

    public Ticket() {
    }

    public Ticket(String parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getParkingLot() {
        return parkingLot;
    }
}
