package model;
import java.time.LocalDate;
import java.util.Objects;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;

    public Reservation(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        return Objects.equals(this.customer, that.customer) &&
                Objects.equals(this.room, that.room) &&
                Objects.equals(this.checkInDate, that.checkInDate) &&
                Objects.equals(this.checkOutDate, that.checkOutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, room, checkInDate, checkOutDate);
    }

    @Override
    public String toString(){
        return String.format("Customer: %s %s%n" +
                        "Room Number: %s%n" +
                        "Price: %.2f per night%n" +
                        "Check-in Date: %s%n" +
                        "Check-out Date: %s%n",
                customer.getFirstName(),
                customer.getLastName(),
                room.getRoomNumber(),
                room.getRoomPrice(),
                checkInDate.toString(),
                checkOutDate.toString());
    }

    public final Customer getCustomer() {
        return customer;
    }

    public final IRoom getRoom() {
        return room;
    }

    public final LocalDate getCheckInDate() {
        return checkInDate;
    }

    public final LocalDate getCheckOutDate() {
        return checkOutDate;
    }
}
