package service;

import model.*;

import java.time.LocalDate;
import java.util.*;


public class ReservationService {
    private static ReservationService reservationService;
    private final Set<IRoom> rooms = new HashSet<>();
    private final Set<Reservation> reservations = new HashSet<>();

    public ReservationService(){}
    public static ReservationService getReservationService(){
        if(reservationService == null){
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    /**
     * @param room type, add a room to the rooms set.
     */
    public void addRoom(IRoom room){
        if(rooms.contains(room)){
            System.out.println("Room Already Exists! Please enter new room.");
        }else{
            rooms.add(room);
        }
    }

    /**
     * @param roomID String type
     * @return Room object, return room from set based on room number.
     */
    public IRoom getARoom(String roomID){
        return rooms.stream()
                .filter(room -> room.getRoomNumber().equals(roomID))
                .findFirst()
                .orElse(null);
    }

    /**
     * @param customer object, contains email, first and last name.
     * @param room object, contains room number, price and room type (single v. double).
     * @param checkInDate LocalDate type, specifies the check-in date of customer.
     * @param checkOutDate LocalDate type, specifies the checkout date of customer.
     * @return Reservation object, contains booking info.
     *
     */
    public Reservation reserveARoom(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate){
        Reservation reservation = new Reservation(customer,room,checkInDate,checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    /**
     * Return a list of rooms by filtering existing reservations
     * for rooms that are unavailable, then return a list of all rooms
     * except for those currently unavailable rooms.
     * Filter for current reserved room based on if the checkout date
     * for those reservations ends before the chosen check-in date or not.
     *
     * @param checkInDate LocalDate type
     * @param checkOutDate LocalDate type
     * @return List of rooms
     */
    public Collection<IRoom> findRooms(LocalDate checkInDate, LocalDate checkOutDate){
        if(checkInDate.isBefore(LocalDate.now()) ||
                checkOutDate.isBefore(LocalDate.now())){
            System.out.println("Check-in date is in the past please pick date after current date:" + LocalDate.now());
            return Collections.emptyList();
        }
        if(checkInDate.isAfter(checkOutDate) ||
                checkInDate.isEqual(checkOutDate) ||
                checkOutDate.isBefore(checkInDate)){
            System.out.println("Date Range not valid, please check your dates and try again.");
            return Collections.emptyList();
        }

        if(reservations.isEmpty() && !rooms.isEmpty()){
            return rooms.stream().toList();
        }

        List<Reservation> unavailableReservation = reservations.stream()
                .filter(reservation -> reservation.getCheckOutDate().isAfter(checkInDate))
                .toList();

        List<IRoom> availableRooms = new ArrayList<>(rooms);

        for (Reservation res : unavailableReservation){
            availableRooms.remove(res.getRoom());
        }

        return availableRooms;
    }

    /**
     * @param customer object, used to filter out current reservations that contains customer.
     * @return List of reservations that are tied to the customer.
     */
    public Collection<Reservation> getCustomerReservation(Customer customer){
        return reservations.stream()
                .filter(reservation -> reservation.getCustomer().equals(customer))
                .toList();
    }

    /**
     * @return list of all rooms in the application.
     */
    public Collection<IRoom> getAllRooms(){
        return rooms.stream().toList();
    }

    /**
     * Prints out all current reservations.
     */
    public void printAllReservation(){
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}
