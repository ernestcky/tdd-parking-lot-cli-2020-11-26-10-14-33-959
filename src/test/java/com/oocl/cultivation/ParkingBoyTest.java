package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Not;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParkingBoyTest {
    @Test
    public void should_return_ticket_when_park_given_car_and_parking_boy_and_parking_lot() throws NotEnoughSpaceException {
        //given
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();

        //when
        Ticket ticket = parkingBoy.park(car);

        //then
        assertNotNull(ticket);
    }

//    @Test
//    public void should_parking_boy_call_parking_lot_park_function_once_when_park_the_car() throws NotEnoughSpaceException {
//        //given
//        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
//        List<ParkingLot> parkingLotList = new ArrayList<>();
//        parkingLotList.add(parkingLot);
//
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
//        Car car = new Car();
//
//        //when
//        parkingBoy.park(car);
//
//        //then
//        verify(parkingLot, times(1)).park(car);
//    }

    @Test
    public void should_not_be_parked_when_park_multiple_cars_given_multiple_car_and_parking_lot_only_1_space() throws NotEnoughSpaceException {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car1 = new Car();
        Car car2 = new Car();

        //when
        Ticket ticket1 = parkingBoy.park(car1);

        //then
        assertNotNull(ticket1);
        NotEnoughSpaceException notEnoughSpaceException = assertThrows(NotEnoughSpaceException.class, () -> {
            parkingBoy.park(car2);
        });
        assertEquals("Not Enough Space", notEnoughSpaceException.getMessage());
    }

    @Test
    public void should_return_car_when_fetch_car_given_parking_lot_that_parked_the_car() throws NotEnoughSpaceException {
        //given
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);

        //when
        final Car actual = parkingLot.fetch(ticket);

        //then
        assertEquals(car, actual);
    }

//    @Test
//    public void should_call_parking_lot_fetch_when_parking_boy_fetch_car() throws NotEnoughSpaceException {
//        //given
//        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
//        List<ParkingLot> parkingLotList = new ArrayList<>();
//        parkingLotList.add(parkingLot);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
//
//        Car car = new Car();
//
//        Ticket ticket = parkingBoy.park(car);
//        //when
//        parkingBoy.fetch(ticket);
//
//        //then
//        verify(parkingLot, times(1)).fetch(ticket);
//    }

    @Test
    public void should_return_null_when_fetch_car_given_used_ticket() throws NotEnoughSpaceException {
        //given
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        Car car = new Car();

        //when
        Ticket ticket = parkingBoy.park(car);
        Car firstFetch = parkingBoy.fetch(ticket);
        Car secondFetch = parkingBoy.fetch(ticket);

        //then
        assertNull(secondFetch);
    }

    @Test
    public void should_return_null_when_fetch_car_given_wrong_ticket() throws NotEnoughSpaceException {
        //given
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        Car car = new Car();
        Ticket wrongTicket = new Ticket();

        //when
        parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(wrongTicket);

        //then
        assertNull(fetchCar);
    }


}
