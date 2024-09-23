package model;

import java.time.LocalDate;

public class Tester {
    public static void main(String[] args){
        Customer customer = new Customer("first","last","j@domain.com");
        System.out.println(customer);
        Room room = new Room("101",10.0, RoomType.SINGLE);
        System.out.println(room);
        Reservation reservation = new Reservation(customer,room, LocalDate.now(),LocalDate.now());
        System.out.println(reservation);
    }
}
