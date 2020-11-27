package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Integer capacity;
    private Map<Ticket, Car> ticketCarMap;
    private Map<Ticket, Boolean> ticketUsedMap;

    public ParkingLot() {
        this.capacity = 1;
        this.ticketCarMap = new HashMap<>();
        this.ticketUsedMap = new HashMap<>();
    }

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
        this.ticketCarMap = new HashMap<>();
        this.ticketUsedMap = new HashMap<>();
    }

    private Boolean checkAvailableSlot() {
        return (this.capacity - this.ticketCarMap.size() > 0);
    }

    private Ticket generateTicket(Car car) {
        Ticket ticket = new Ticket();
        this.ticketCarMap.put(ticket, car);
        this.ticketUsedMap.put(ticket, false);
        return ticket;
    }

    public Ticket park(Car car) throws NotEnoughSpaceException {
        if (!this.checkAvailableSlot()) {
            throw new NotEnoughSpaceException();
        }
        return this.generateTicket(car);
    }

    private Boolean isTicketUsed(Ticket ticket) {
        return this.ticketUsedMap.get(ticket);
    }

    public Car fetch(Ticket ticket) {
        if (!this.isTicketUsed(ticket)) {
            this.ticketUsedMap.put(ticket, true);
            return this.ticketCarMap.get(ticket);
        }
        return null;
    }
}
