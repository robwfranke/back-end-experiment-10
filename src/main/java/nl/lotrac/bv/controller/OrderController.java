package nl.lotrac.bv.controller;

import nl.lotrac.bv.model.MessageFrontEnd;
import nl.lotrac.bv.model.Order;
import nl.lotrac.bv.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/orders")


public class OrderController {

    @Autowired
    private OrderService orderService;


//********************************************************************************
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewOrder(@RequestBody Order order) {
        String newOrderName = orderService.createNewOrder(order);
        MessageFrontEnd message = new MessageFrontEnd("Order: " + newOrderName + "  created");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{create}")
                .buildAndExpand(newOrderName).toUri();
        return ResponseEntity.created(location).body(message);
    }
//********************************************************************************


//********************************************************************************
    @GetMapping(value = "")
    public ResponseEntity<Object> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }
//********************************************************************************


//********************************************************************************
    @GetMapping(value = "/ordersByCustomer/{username}")
    public ResponseEntity<Object> getAllordersByCustomer(@PathVariable("username") String username) {
        List<Order> orders = orderService.getAllOrdersByUser(username);
        return ResponseEntity.ok().body(orders);
    }
//********************************************************************************


//********************************************************************************
    @GetMapping(value = "/inlog")
    public ResponseEntity<Object> getAllordersByInlogNameOnly() {
        String user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        List<Order> orders = orderService.getAllOrdersByUser(user);
        return ResponseEntity.ok().body(orders);
    }
//********************************************************************************


//********************************************************************************
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getOneOrderByID(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderService.getOneOrderByID(id), HttpStatus.OK);
    }
//********************************************************************************


//********************************************************************************
    @GetMapping(value = "/name/{ordername}")
    public ResponseEntity<Object> getOneOrderByName(@PathVariable("ordername") String ordername) {
        return ResponseEntity.ok().body(orderService.getOneOrderByName(ordername));
    }
//********************************************************************************


//********************************************************************************
    @PutMapping(value = "/update/{ordername}")

    public ResponseEntity<Object> updateOrder(@PathVariable("ordername") String ordername, @RequestBody Order order) {
        orderService.updateOrder(ordername, order);
        return ResponseEntity.noContent().build();
    }
//********************************************************************************


}