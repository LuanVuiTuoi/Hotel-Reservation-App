package model;
import java.util.Objects;

public class Room implements IRoom{
    private String roomNumber;
    private double price;
    private RoomType enumeration;

    public Room(String roomNumber, double price, RoomType enumeration){
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return Objects.equals(roomNumber, room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(roomNumber);
    }

    @Override
    public String toString(){
        String roomTypeName = getRoomType() == RoomType.SINGLE ? "Single Bed" : "Double Bed";
        return String.format("Room Number: %s%n" +
                        "Room Price: $%.2f%n" +
                        "Room Type: %s%n",
                        roomNumber,
                        price,
                        roomTypeName);
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEnumeration(RoomType enumeration) {
        this.enumeration = enumeration;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getRoomPrice() {
        return price;
    }

    public RoomType getRoomType() {
        return enumeration;
    }

    public boolean isFree() {
        return false;
    }

}
