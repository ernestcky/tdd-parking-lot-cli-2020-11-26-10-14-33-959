package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.strategy.ParkingStrategy;
import com.oocl.cultivation.strategy.SuperSmartParkingStrategy;
import com.oocl.cultivation.utilities.Car;
import com.oocl.cultivation.utilities.ParkingLot;
import com.oocl.cultivation.utilities.Ticket;
import com.oocl.cultivation.exception.CarNotFoundException;
import com.oocl.cultivation.exception.NotEnoughSpaceException;

import java.util.List;

public class SuperSmartParkingBoy  {
    private List<ParkingLot> parkingLotList;
    private ParkingStrategy parkingStrategy = new SuperSmartParkingStrategy();

    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Ticket park(Car car) throws NotEnoughSpaceException {  //TODO: put the function to parkingLot
        return this.parkingStrategy.park(car, parkingLotList);
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
