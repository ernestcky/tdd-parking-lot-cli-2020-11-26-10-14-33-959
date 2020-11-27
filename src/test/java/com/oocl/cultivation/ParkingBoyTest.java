package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ParkingBoyTest {
    @Test
    public void should_return_ticket_when_park_given_car_and_parking_boy_and_parking_lot() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        //when
        Ticket ticket = parkingBoy.park(car);

        //then
        assertNotNull(ticket);
    }

    @Test
    public void should_parking_boy_call_parking_lot_park_function_once_when_park_the_car() {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        //when
        parkingBoy.park(car);

        //then
        verify(parkingLot, times(1)).park(car);
    }

    @Test
    public void should_not_be_parked_when_park_multiple_cars_given_multiple_car_and_parking_lot_only_1_space() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
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
    public void should_return_car_when_fetch_car_given_parking_lot_that_parked_the_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);

        //when
        final Car actual = parkingLot.fetch(ticket);

        //then
        assertEquals(car, actual);
    }

    @Test
    public void should_call_parking_lot_fetch_when_parking_boy_fetch_car() {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        Ticket ticket = parkingBoy.park(car);
        //when
        parkingBoy.fetch(ticket);

        //then
        verify(parkingLot, times(1)).fetch(ticket);
    }

    @Test
    public void should_return_null_when_fetch_car_given_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        //when
        Ticket ticket = parkingBoy.park(car);
        Car firstFetch = parkingBoy.fetch(ticket);
        Car secondFetch = parkingBoy.fetch(ticket);

        //then
        assertNull(secondFetch);
    }

//    @Test
//    public void should_throw_not_enough_space_exception_when_park_given_full_parking_lot() {
//        //given
//        ParkingLot parkingLot = new ParkingLot(1);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
//        parkingBoy.park(new Car());
//
//        //when
//        NotEnoughSpaceException notEnoughSpaceException = assertThrows(NotEnoughSpaceException.class, () -> {})
//        parkingBoy.park(new Car);
//
//        //then
//
//    }


}
