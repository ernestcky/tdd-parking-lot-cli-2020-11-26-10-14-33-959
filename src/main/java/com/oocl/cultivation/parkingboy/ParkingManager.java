package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.utilities.Car;
import com.oocl.cultivation.utilities.ParkingLot;
import com.oocl.cultivation.utilities.Ticket;
import com.oocl.cultivation.exception.CarNotFoundException;
import com.oocl.cultivation.exception.NotEnoughSpaceException;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager {
    private List<ParkingBoy> parkingBoyList;
    private List<ParkingLot> parkingLotList;

    public ParkingManager() {
        super();
        this.parkingBoyList = new ArrayList<>();
    }

    public ParkingManager(List<ParkingLot> parkingLotList, List<ParkingBoy> parkingBoyList) {
        this.parkingLotList = parkingLotList;
        if (parkingBoyList == null) {
            this.parkingBoyList = new ArrayList<>();
        } else {
            this.parkingBoyList = parkingBoyList;
        }
    }

    public List<ParkingBoy> getParkingBoyList() {
        return this.parkingBoyList;
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        this.parkingBoyList.add(parkingBoy);
    }

    public ParkingBoy getParkingBoy(Integer index) {
        return this.parkingBoyList.get(index);
    }

    public Ticket park(Car car) throws NotEnoughSpaceException {
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.isHasAvailableSlot()) {
                return parkingLot.park(car);
            }
        }
        throw new NotEnoughSpaceException("Not Enough Space");
    }

    public Car fetch(Ticket ticket) throws CarNotFoundException {
        for (ParkingLot parkingLot : parkingLotList) {
            if (ticket.getParkingLot() != null && ticket.getParkingLot().equals(parkingLot.getParkingLotName())) {
                return parkingLot.fetch(ticket);
            }
        }
        throw new CarNotFoundException("Car Not Found");
    }
}
