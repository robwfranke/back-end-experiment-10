package nl.lotrac.bv.service;

import nl.lotrac.bv.controller.model.CreateCustomerWithAddress;
import nl.lotrac.bv.model.User;

import java.util.List;


public interface CustomerService {


    public abstract User createNewCustomer(User user);


    public abstract User createCustomerWithAddress(CreateCustomerWithAddress  createCustomerWithAddress);

//    public abstract List<Customer> getAllCustomers();
//
//    public abstract Customer getOneCustomerByID(Long id);
//
//
//    public abstract Customer getOneCustomerByName(String customer);


}