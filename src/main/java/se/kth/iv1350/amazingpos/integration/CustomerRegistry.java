package se.kth.iv1350.amazingpos.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * This dummy implementation of customer registry.
 *
 * Represents a database for customers who are eligible to discount
 */
public class CustomerRegistry {
    List<CustomerRegistry> customers = new ArrayList< >();
    private int age;
    private String name;
    private String address;
    private String customerID;
    private int telephoneNumber;

    /**
     * Creates a new instance of a fake customer registry that contains
     * customers.
     */
    public CustomerRegistry() {
        addCustomer();
    }

    /**
     * Creates a new instance.
     *
     * @param name     The customer’s name.
     * @param address    The customer’s address.
     * @param customerID    The customer’s ID
     * @param telephoneNumber  The customer’s phone number
     * @param age                The customer’s age
     */
    public CustomerRegistry(String name, String address, String customerID, int telephoneNumber, int age) {
        this.age = age;
        this.name = name;
        this.address = address;
        this.customerID = customerID;
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Chek if specific customer exists in customer registry
     * @param customerID Customer's identifier
     * @return an instance of already existing customer
     * otherwise return null value and print out a message
     */
    public CustomerRegistry customerControl(String customerID){
        for (CustomerRegistry customer : customers)
            if(customer.customerID.equals(customerID))
                return customer;
        {
            System.out.println("this customer's ID does not exist");
            return null;
        }
    }

    /**
     * Get the value of age
     *
     * @return the value of age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Get the value of age customer's ID
     *
     * @return the value of customer's ID
     */
    public String getCustomerID() {
        return this.customerID;
    }

    /**
     * Simulates registered customers  in customer registry
     */
    private void  addCustomer(){
        customers.add(new CustomerRegistry("Erik", "Stockholm", "11111", 1, 35 ));
        customers.add(new CustomerRegistry("Johan", "Kista", "22222", 2, 44 ));
        customers.add(new CustomerRegistry("Anders", "Kungsholm", "33333", 3, 33 ));
    }


}
