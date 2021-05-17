package nl.lotrac.bv.service;

import nl.lotrac.bv.exceptions.NameNotFoundException;
import nl.lotrac.bv.model.Role;
import nl.lotrac.bv.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserService userService;




    @Override
    public User createNewCustomer(User user) {

        String newUser = userService.createUser(user);
        userService.addAuthority(newUser, Role.CUSTOMER);

        System.out.println("CustomerService Impl create newCustomer");

//        if (customerRepository.getCustomerByCustomername(customer.getCustomername()) != null)
//            throw new NameExistsException("customer exists");

        return userService.getUser(newUser);

    }




//    @Override
//    public List<Customer> getAllCustomers() {
//
//        return customerRepository.findAll();
//    }
//
//
//
//    @Override
//    public Customer getOneCustomerByID(Long id) {
//
//        System.out.println("CustomerServiceImpl");
//        Optional<Customer> customer = customerRepository.findById(id);
//        if (customer.isEmpty()) {
//            throw new NameNotFoundException("customer does not exists");
//        } else {
//            return customer.get();
//        }
//    }
//
//
//    @Override
//    public Customer getOneCustomerByName(String customername) {
//        System.out.println("CustomerServiceImpl getOneCustomerByName");
//        Customer customer = customerRepository.getCustomerByCustomername(customername);
//        System.out.println("customer1");
//        if (customer == null)
//            throw new NameNotFoundException("customer does not exists");
//        return customer;
//    }




}
