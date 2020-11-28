package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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


    @Test
    public void should_return_two_ticket_when_park_two_car_given_parking_boy_and_two_parking_lot() throws NotEnoughSpaceException {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot());
        parkingLotList.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        //when
        Ticket ticket1 = parkingBoy.park(new Car());
        Ticket ticket2 = parkingBoy.park(new Car());

        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);

    }

    @Test
    public void should_return_two_car_when_fetch_two_car_given_two_valid_ticket_from_two_parking_lot() throws NotEnoughSpaceException {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot());
        parkingLotList.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        //when
        Car car1 = new Car();
        Car car2 = new Car();

        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);

        //then
        assertEquals(car1, parkingBoy.fetch(ticket1));
        assertEquals(car2, parkingBoy.fetch(ticket2));
    }

    @Test
    public void should_park_cars_to_parking_lot_with_more_capacity_when_park_given_two_parking_lot() throws NotEnoughSpaceException {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(5);
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLotList);

        //when
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);
        Ticket ticket3 = parkingBoy.park(car3);

        //then
        assertEquals(parkingLotA.getNumberOfCar(), 0);
        assertEquals(parkingLotB.getNumberOfCar(), 3);
    }

    @Test
    public void should_return_two_car_when_fetch_two_car_given_two_valid_ticket_from_two_parking_lot_and_smart_parking_boy() throws NotEnoughSpaceException {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot());
        parkingLotList.add(new ParkingLot());
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLotList);

        //when
        Car car1 = new Car();
        Car car2 = new Car();

        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);

        //then
        assertEquals(car1, parkingBoy.fetch(ticket1));
        assertEquals(car2, parkingBoy.fetch(ticket2));
    }

    @Test
    public void should_park_cars_to_parking_lot_with_higher_available_rate_when_park_given_three_parking_lot() throws NotEnoughSpaceException {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(3);
        ParkingLot parkingLotC = new ParkingLot(10);
        parkingLotList.add(parkingLotA);
        parkingLotList.add(parkingLotB);
        parkingLotList.add(parkingLotC);
        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLotList);

        //when
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();
        Car car6 = new Car();

        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);
        Ticket ticket3 = parkingBoy.park(car3);
        Ticket ticket4 = parkingBoy.park(car4);
        Ticket ticket5 = parkingBoy.park(car5);
        Ticket ticket6 = parkingBoy.park(car6);

        //then
        assertEquals(1, parkingLotA.getNumberOfCar());
        assertEquals(1, parkingLotB.getNumberOfCar());
        assertEquals(4, parkingLotC.getNumberOfCar());
    }

    @Test
    public void should_successfully_add_parking_boy_to_management_list_when_add_parking_boy_given_manager() {
        //given
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        ParkingBoy parkingBoyA = new ParkingBoy();
        ParkingBoy parkingBoyB = new ParkingBoy();
        ParkingBoy parkingBoyC = new ParkingBoy();

        parkingBoyList.add(parkingBoyA);
        parkingBoyList.add(parkingBoyB);
        parkingBoyList.add(parkingBoyC);

        ParkingManager parkingManager = new ParkingManager();

        //when
        for (ParkingBoy parkingBoy : parkingBoyList) {
            parkingManager.addParkingBoy(parkingBoy);
        }

        //then
        assertEquals(parkingBoyA, parkingManager.getParkingBoyList().get(0));
        assertEquals(parkingBoyB, parkingManager.getParkingBoyList().get(1));
        assertEquals(parkingBoyC, parkingManager.getParkingBoyList().get(2));

    }

    @Test
    public void should_return_return_ticket_when_order_parking_boy_park_given_manager_and_some_car() throws NotEnoughSpaceException {

        //given
        List<ParkingLot> parkingLotList1 = new ArrayList<>();
        parkingLotList1.add(new ParkingLot(1));

        List<ParkingLot> parkingLotList2 = new ArrayList<>();
        parkingLotList2.add(new ParkingLot(1));
        parkingLotList2.add(new ParkingLot(10));

        List<ParkingLot> parkingLotList3 = new ArrayList<>();
        parkingLotList3.add(new ParkingLot(2));
        parkingLotList3.add(new ParkingLot(5));

        List<ParkingLot> parkingLotList4 = new ArrayList<>();
        parkingLotList4.add(new ParkingLot(5));

        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(new ParkingBoy(parkingLotList1));
        parkingBoyList.add(new ParkingBoy(parkingLotList2));
        parkingBoyList.add(new ParkingBoy(parkingLotList3));

        ParkingManager parkingManager = new ParkingManager(parkingBoyList, parkingLotList4);

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();

        //when
        Ticket ticket1 = parkingManager.getParkingBoy(0).park(car1);
        Ticket ticket2 = parkingManager.getParkingBoy(1).park(car2);
        Ticket ticket3 = parkingManager.getParkingBoy(2).park(car3);
        Ticket ticket4 = parkingManager.getParkingBoy(1).park(car4);
        Ticket ticket5 = parkingManager.getParkingBoy(2).park(car5);

        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);
        assertNotNull(ticket3);
        assertNotNull(ticket4);
        assertNotNull(ticket5);

    }

}
