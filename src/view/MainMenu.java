package view;

import api.HotelResource;
import model.IRoom;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Scanner;

/**
 * @author Luan Nguyen
 * @implNote Cognizant Java Course Project
 */
public class MainMenu {
    static HotelResource hotelResource = HotelResource.getHotelResource();

    public MainMenu() {
        init();
    }

    public static void init(){
        String title = """
                --------------------------------------------------------------
                Welcome To Our Hotel!
                    * please select an option using the corresponding number *
                --------------------------------------------------------------
                """;
        System.out.println(title);
        menu();
    }

    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        int selection;

        String options = """
                ---------------------------
                1. Find and reserve a room
                2. See my reservations
                3. Create an account
                4. Admin
                5. Exit
                ---------------------------
                """;
        while (run){
            try {
                System.out.println(options);
                selection = Integer.parseInt(scanner.nextLine());
                switch (selection){
                    case 1:
                        reserveARoom();
                        break;
                    case 2:
                        checkReservation();
                        break;
                    case 3:
                        createAccount();
                        break;
                    case 4:
                        adminMenu();
                        break;
                    case 5:
                        System.out.println("Thank you for reserving a room with us. See you again!");
                        run = false;
                        break;
                    default:
                        System.out.println("Option Invalid! Please choose again.");
                        break;

                }
            }catch (Exception e){
                System.out.println("Invalid Input! Try Again");
            }
        }

    }

    /**
     * Ask customer for date range to check for available rooms.
     * Print out all available rooms.
     * Validate that there are available rooms.
     * Check that room and customer accounts exists.
     * Create a reservations for customer.
     */
    public static void reserveARoom(){
        LocalDate checkInDate;
        LocalDate checkoutDate;
        String email;
        String roomNumber;
        Collection<IRoom> availableRooms;

        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Please choose your check-in and check-out date:");
            System.out.println("Check-in Date: Format YYYY-MM-DD");
            checkInDate = LocalDate.parse(scanner.nextLine());
            System.out.println("Check-out Date: Format YYYY-MM-DD");
            checkoutDate = LocalDate.parse(scanner.nextLine());

            availableRooms = hotelResource.findARoom(checkInDate,checkoutDate);

            if(!availableRooms.isEmpty()){
                System.out.println("List of Available Rooms:");
                for (IRoom room : availableRooms){
                    System.out.println("------");
                    System.out.print(room);
                }

                System.out.println();
                System.out.println("Please select a room number you would like to reserve");
                roomNumber = scanner.nextLine();
                System.out.println("Please enter your email for the reservation");
                email = scanner.nextLine();

                if(hotelResource.getRoom(roomNumber) == null){
                    System.out.println("Failed to book reservation");
                    System.out.println("Please check if Room exists!");
                    System.out.println("Please use Admin Service page to add rooms.\n");
                }else{
                    if(hotelResource.getCustomer(email) == null){
                        System.out.println("Failed to book reservation");
                        System.out.println("Please create your account before continuing.\n");
                        createAccount();
                    }
                    System.out.println("Reservation booked successfully\n");
                    System.out.println(hotelResource.bookARoom(email,
                            hotelResource.getRoom(roomNumber),
                            checkInDate,checkoutDate));

                }

            }else{
                System.out.println("No Rooms Available at current date range. Please contact admin.");
            }

        }catch (Exception e){
            System.out.println("Something Went Wrong! Try Again");
            System.out.println(e.getMessage());
        }

        System.out.println("Returning to Menu...");
    }

    /**
     * Validate user accounts using their email as the identifier.
     * If email is valid, print out all reservations for customer.
     */
    public static void checkReservation(){
        String email;
        Scanner scanner = new Scanner(System.in);

        try{
            System.out.println("Please enter your email to get your reservations");
            email = scanner.nextLine();
            if(hotelResource.getCustomer(email) == null){
                System.out.println("Account not found! Try Again");
            }else{
                hotelResource.getCustomerReservations(email).forEach(System.out::println);
            }

        }catch (Exception e){
            System.out.println("Something Went Wrong! Try Again");
            System.out.println(e.getMessage());
        }

        System.out.println("Returning to Menu...");
    }

    /**
     * Create new customer using email, first name and last name..
     */
    public static void createAccount(){
        String email;
        String firstName;
        String lastName;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Create an Account!");
        System.out.println("Please enter your email:");
        email = scanner.nextLine();
        System.out.println("Please enter your first name:");
        firstName = scanner.nextLine();
        System.out.println("Please enter your last name:");
        lastName = scanner.nextLine();

        try{
            hotelResource.createACustomer(email,firstName,lastName);
        }catch (Exception e){
            System.out.println("Something Went Wrong! Try Again");
            System.out.println(e.getMessage());
        }

        System.out.println("Returning to Menu...");
    }
    public static void adminMenu(){
        String message = """
                Leaving Hotel Services Page...
                Transferring to Admin Services Page...
                """;
        System.out.println(message);
        AdminMenu.init();
    }

    public static void main(String[] args){
        new MainMenu();
    }
}
