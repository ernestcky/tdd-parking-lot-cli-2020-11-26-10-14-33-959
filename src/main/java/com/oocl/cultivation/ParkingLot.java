package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Integer capacity;
    private Map<Ticket, Car> ticketCarMap;

    public ParkingLot() {
        this.capacity = 1;
        this.ticketCarMap = new HashMap<>();
    }

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
        this.ticketCarMap = new HashMap<>();
    }

    private Boolean checkAvailableSlot() {
        return (this.capacity - this.ticketCarMap.size() > 0);
    }

    public Ticket park(Car car) {
        if (!this.checkAvailableSlot()) {
            return null;
        }
        Ticket ticket = new Ticket();
        this.ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return null;
    }
}
