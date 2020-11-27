package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private final Integer capacity;
    private List<Car> carList;

    public ParkingLot() {
        this.capacity = 1;
        this.carList = new ArrayList<>();
    }

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
        this.carList = new ArrayList<>();
    }

    public Ticket park(Car car) {
        if (capacity - carList.size() <= 0) {
            return null;
        }
        carList.add(car);
        return new Ticket();
    }
}
