package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.strategy.ParkingStrategy;
import com.oocl.cultivation.strategy.StandardParkingStrategy;
import com.oocl.cultivation.utilities.Car;
import com.oocl.cultivation.utilities.ParkingLot;
import com.oocl.cultivation.utilities.Ticket;
import com.oocl.cultivation.exception.CarNotFoundException;
import com.oocl.cultivation.exception.NotEnoughSpaceException;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;
    ParkingStrategy parkingStrategy = new StandardParkingStrategy();

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
        return this.parkingStrategy.park(car, this.parkingLotList);
    }
    public Car fetch(Ticket ticket) throws CarNotFoundException {
        for (ParkingLot parkingLot : this.parkingLotList) {
            if (ticket.getParkingLot() != null && ticket.getParkingLot().equals(parkingLot.getParkingLotName())) {
                return parkingLot.fetch(ticket);
            }
        }
        throw new CarNotFoundException("Car Not Found");
    }
}
