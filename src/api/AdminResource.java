package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    public CustomerService customerService = CustomerService.getCustomerService();
    public ReservationService reservationService = ReservationService.getReservationService();

    private static AdminResource adminResource;

    public AdminResource() {
    }

    public static AdminResource getAdminResource() {
        if(adminResource == null){
            adminResource = new AdminResource();
        }

        return adminResource;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }
    public void addRoom(List<IRoom> rooms){
        for(IRoom room : rooms){
            reservationService.addRoom(room);
        }
    }
    public Collection<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }
    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomer();
    }
    public void displayAllReservation(){
        reservationService.printAllReservation();
    }
}
