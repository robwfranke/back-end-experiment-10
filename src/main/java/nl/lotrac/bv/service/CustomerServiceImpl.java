package nl.lotrac.bv.service;

import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.controller.model.CreateCustomerWithAddress;
import nl.lotrac.bv.exceptions.NameExistsException;
import nl.lotrac.bv.model.Address;
import nl.lotrac.bv.model.Role;
import nl.lotrac.bv.model.User;
import nl.lotrac.bv.repository.UserRepository;
import nl.lotrac.bv.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private CustomerService customerService;


    @Override
    public User createCustomerWithAddress(CreateCustomerWithAddress createCustomerWithAddress) {

        log.debug("CustomerServiceImpl createCustomerWithAddress " + createCustomerWithAddress.toString());


        if (userRepository.existsById(createCustomerWithAddress.getUsername()))
            throw new NameExistsException(createCustomerWithAddress.getUsername() + "  exists!!");

        User user = userRepository.getUserByUsername(createCustomerWithAddress.getUsername());


        log.debug("user bestaat nog niet");


        log.debug("username:   " + createCustomerWithAddress.getUsername());
        log.debug("Password:   " + createCustomerWithAddress.getPassword());
//        log.debug(""+createCustomerWithAddress.get);
        log.debug("street:    " + createCustomerWithAddress.getStreet());

        User newUser = new User();


        Address newAddress = new Address();

        newUser.setUsername(createCustomerWithAddress.getUsername());
        log.debug("ln64 Newusername" + createCustomerWithAddress.getUsername());

        newUser.setPassword(passwordEncoder.encode(createCustomerWithAddress.getPassword()));
        newUser.setEnabled(true);

        newAddress.setStreet(createCustomerWithAddress.getStreet());


        String realName = newUser.getUsername();
        log.debug("realName:  " + realName);


        log.debug("newUser" + newUser.toString());


        log.debug("newAddress:  " + newAddress.toString());

        addressRepository.save(newAddress);
        userRepository.save(newUser);
        userService.addAuthority(realName, Role.CUSTOMER);

//        en nu nog de foreignkey

        return (newUser);
    }


    @Override
    public User createNewCustomer(User user) {

        String newUser = userService.createUser(user);
        userService.addAuthority(newUser, Role.CUSTOMER);

        System.out.println("CustomerService Impl create newCustomer");

        return userService.getUser(newUser);

    }


}
