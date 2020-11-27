package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private Integer availableSpace;

    public ParkingLot() {
        this.availableSpace = 1;
    }

    public ParkingLot(Integer availableSpace) {
        this.availableSpace = availableSpace;
    }

    public Ticket park(Car car) {
        return new Ticket();
    }
}
