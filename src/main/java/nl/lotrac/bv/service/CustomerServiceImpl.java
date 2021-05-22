package nl.lotrac.bv.service;

import nl.lotrac.bv.model.Role;
import nl.lotrac.bv.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



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

        return userService.getUser(newUser);

    }








}
