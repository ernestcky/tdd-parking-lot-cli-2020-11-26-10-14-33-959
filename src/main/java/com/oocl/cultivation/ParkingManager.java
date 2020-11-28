package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager extends ParkingBoy {
    private List<ParkingBoy> parkingBoyList;

    public ParkingManager() {
        super();
        this.parkingBoyList = new ArrayList<>();
    }

    public ParkingManager(List<ParkingBoy> parkingBoyList, List<ParkingLot> parkingLotList) {
        super(parkingLotList);
        this.parkingBoyList = parkingBoyList;
    }

    public List<ParkingBoy> getParkingBoyList() {
        return parkingBoyList;
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        this.parkingBoyList.add(parkingBoy);
    }

}
