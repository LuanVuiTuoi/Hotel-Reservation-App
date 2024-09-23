package service;

import model.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private static CustomerService customerService;
    private final Map<String,Customer> customers = new HashMap<>();

    public CustomerService() {
    }

    public static CustomerService getCustomerService() {
        if(customerService == null){
            customerService = new CustomerService();
        }
        return customerService;
    }

    /**
     * Check whether customer email already exists,
     * if not add customer to the customers map.
     * @param email String type
     * @param firstName String type
     * @param lastName String type
     */
    public void addCustomer(String email, String firstName, String lastName){
        if(customers.containsKey(email)){
            System.out.println("Customer Email Already Exists! Please try another.");
        }else{
            customers.put(email, new Customer(firstName,lastName,email));
            System.out.println("Account Created For: " + email);
        }
    }

    /**
     * @param customerEmail String type
     * @return Customer object based on customer email.
     */
    public Customer getCustomer(String customerEmail){
        return customers.get(customerEmail);
    }

    /**
     * @return List of all customer object.
     */
    public Collection<Customer> getAllCustomer(){
        return customers.values();
    }
}
