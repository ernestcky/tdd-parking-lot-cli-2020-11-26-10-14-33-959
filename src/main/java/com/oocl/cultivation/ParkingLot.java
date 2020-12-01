package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Integer capacity;
    private Map<Ticket, Car> ticketCarMap;
    // todo: duplicated
    private Map<Ticket, Boolean> ticketMap;

    public ParkingLot() {
        this.capacity = 1;
        this.ticketCarMap = new HashMap<>();
        this.ticketMap = new HashMap<>();
    }

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
        this.ticketCarMap = new HashMap<>();
        this.ticketMap = new HashMap<>();
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getRemainingPlace() {
        return this.capacity - this.getNumberOfCar();
    }

    public Integer getNumberOfCar() {
        return this.ticketCarMap.size();
    }

    public Boolean isHasAvailableSlot() {
        return (this.getRemainingPlace() > 0);
    }

    private Ticket generateTicket(Car car) {
        Ticket ticket = new Ticket(this.toString());
        this.ticketCarMap.put(ticket, car);
        this.ticketMap.put(ticket, false);
        return ticket;
    }

    public Ticket park(Car car) throws NotEnoughSpaceException {
        if (!this.isHasAvailableSlot()) {
            throw new NotEnoughSpaceException("Not Enough Space");
        }
        return this.generateTicket(car);
    }

    private Boolean isTicketValid(Ticket ticket) {
        if (this.ticketMap.get(ticket) != null) {
            return this.ticketMap.get(ticket);
        }
        return false;
    }

    public Car fetch(Ticket ticket) {
        if (!this.isTicketValid(ticket)) {
            this.ticketMap.put(ticket, true);
            Car returnCar = this.ticketCarMap.get(ticket);
            this.ticketCarMap.remove(ticket);
            return returnCar;
        }
        return null;
    }
}
