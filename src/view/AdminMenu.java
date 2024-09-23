package view;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.time.LocalDate;
import java.util.*;
import java.util.random.RandomGenerator;

public class AdminMenu {
    static AdminResource adminResource = AdminResource.getAdminResource();

    public static void init(){
        String title = """
                --------------------------------------------------------------
                Welcome To The Admin Services
                    * please select an option using the corresponding number *
                --------------------------------------------------------------
                """;
        System.out.println(title);
        menu();
    }
    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        int selection;
        boolean run = true;
        String options = """
                ----------------------------
                1. See all Customer
                2. See all Rooms
                3. See all Reservation
                4. Add a Room
                5. Populate Test Data
                6. Return to Hotel Services
                ----------------------------
                """;
        while (run){
            System.out.println(options);
            selection = Integer.parseInt(scanner.nextLine());
            switch (selection) {
                case 1:
                    getAllCustomers();
                    break;
                case 2:
                    getAllRooms();
                    break;
                case 3:
                    getAllReservations();
                    break;
                case 4:
                    addRoom();
                    break;
                case 5:
                    populateTestData();
                    break;
                case 6:
                    System.out.println("Returning to Hotel Services Page...");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid selection! Please choose again.");
                    break;
            }
        }
    }

    public static void getAllCustomers() {
        List<Customer> allCustomers;
        try {
            allCustomers = adminResource.getAllCustomers().stream().toList();
            if(allCustomers.isEmpty()){
                System.out.println("No Customer Exists!");
            }
            allCustomers.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getAllRooms() {
        List<IRoom> allRooms;
        try {
            allRooms = adminResource.getAllRooms().stream().toList();
            if(allRooms.isEmpty()){
                System.out.println("No room exists!");
            }
            allRooms.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getAllReservations() {
        try {
            adminResource.displayAllReservation();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addRoom() {
        List<IRoom> rooms = new ArrayList<>();
        int numRooms;
        String roomNumber;
        double price;
        String roomType;
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("How many rooms would you like to add?");
            numRooms = Integer.parseInt(scanner.nextLine());

            while(numRooms != 0){
                System.out.println("Enter Room Number:");
                roomNumber = scanner.nextLine();
                System.out.println("Enter Room Price: ");
                price = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter Room Type: single or double");
                roomType = scanner.nextLine();

                if(roomType.equalsIgnoreCase("single")){
                    rooms.add(new Room(roomNumber,price, RoomType.SINGLE));
                }else if(roomType.equalsIgnoreCase("double")){
                    rooms.add(new Room(roomNumber,price, RoomType.DOUBLE));
                }else{
                    System.out.println("Please choose correct room type. ex single or double.");
                    continue;
                }

                numRooms--;
            }

            adminResource.addRoom(rooms);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void populateTestData() {
        List<IRoom> rooms = new ArrayList<>();
        try {
            MainMenu.hotelResource.createACustomer("luan@domain.com","luan","nguyen");
            MainMenu.hotelResource.createACustomer("john@domain.com","john","doe");
            MainMenu.hotelResource.createACustomer("jane@domain.com","jane","doe");

            // Populate List of Rooms
            for(int rNum = 100; rNum < 105; rNum++){
                rooms.add(new Room(
                        Integer.toString(rNum),
                        RandomGenerator.getDefault().nextDouble(999.99),
                        (rNum % 2 == 0) ? RoomType.SINGLE : RoomType.DOUBLE
                        ));
            }
            adminResource.addRoom(rooms.stream().toList());

            MainMenu.hotelResource.bookARoom(
                    "john@domain.com",
                    new Room("106", 999.99,RoomType.DOUBLE),
                    LocalDate.now(),
                    LocalDate.now().plusDays(1)
            );

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
