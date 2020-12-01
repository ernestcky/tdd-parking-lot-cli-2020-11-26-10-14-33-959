package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.utilities.Car;
import com.oocl.cultivation.utilities.ParkingLot;
import com.oocl.cultivation.utilities.Ticket;
import com.oocl.cultivation.exception.CarNotFoundException;
import com.oocl.cultivation.exception.NotEnoughSpaceException;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;

    public ParkingBoy() {
        this.parkingLotList = new ArrayList<>();
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        if (parkingLotList == null) {
            this.parkingLotList = new ArrayList<>();
        } else {
            this.parkingLotList = parkingLotList;
        }
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public Ticket park(Car car) throws NotEnoughSpaceException {
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.isHasAvailableSlot()) {
                return parkingLot.park(car);
            }
        }
        throw new NotEnoughSpaceException("Not Enough Space");
    }
        // todo : remove car, and this.
    public Car fetch(Ticket ticket) throws CarNotFoundException {
        Car car;
        //todo: add a funciton to get parkinglot name
        for (ParkingLot parkingLot : parkingLotList) {
            if (ticket.getParkingLot() != null && ticket.getParkingLot().equals(parkingLot.toString())) {
                return parkingLot.fetch(ticket);
            }
        }
        throw new CarNotFoundException("Car Not Found");
    }
}
