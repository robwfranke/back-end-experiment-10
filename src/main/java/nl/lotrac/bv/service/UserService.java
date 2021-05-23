package nl.lotrac.bv.service;

import nl.lotrac.bv.controller.model.CreateUserWithAddress;
import nl.lotrac.bv.model.Authority;
import nl.lotrac.bv.model.Role;
import nl.lotrac.bv.model.User;

import java.util.Collection;
import java.util.Set;



public interface UserService {


    User createUserWithAddress(CreateUserWithAddress user);

    public abstract String createUser(User user);

    public abstract void updateUser(String username, User user);

    public abstract void deleteUser(String username);

    public abstract Collection<User> getUsers();

    public abstract User getUser(String username);

    public abstract boolean userExists(String username);

    public abstract Collection<User> getAllAuthorities();
    public abstract Set<Authority> getAuthorities(String username);
    public abstract void addAuthority(String username, Role authority);
    public abstract void removeAuthority(String username, Role authority);
}