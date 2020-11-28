package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager extends ParkingBoy {
    private List<ParkingBoy> parkingBoyList;

    public ParkingManager() {
        super();
        this.parkingBoyList = new ArrayList<>();
    }

    public ParkingManager(List<ParkingLot> parkingLotList, List<ParkingBoy> parkingBoyList) {
        super(parkingLotList);
        if (parkingBoyList == null) {
            this.parkingBoyList = new ArrayList<>();
        } else {
            this.parkingBoyList = parkingBoyList;
        }
    }

    public List<ParkingBoy> getParkingBoyList() {
        return parkingBoyList;
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        this.parkingBoyList.add(parkingBoy);
    }

    public ParkingBoy getParkingBoy(Integer index) {
        return this.parkingBoyList.get(index);
    }

}
