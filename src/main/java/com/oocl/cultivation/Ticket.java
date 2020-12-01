package com.oocl.cultivation;

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
