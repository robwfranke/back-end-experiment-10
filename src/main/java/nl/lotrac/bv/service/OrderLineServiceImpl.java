package nl.lotrac.bv.service;


import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.controller.model.CreateOrderLine;
import nl.lotrac.bv.exceptions.NameExistsException;
import nl.lotrac.bv.exceptions.NameNotFoundException;
import nl.lotrac.bv.model.Order;
import nl.lotrac.bv.model.OrderLine;
import nl.lotrac.bv.model.User;
import nl.lotrac.bv.repository.OrderLineRepository;
import nl.lotrac.bv.repository.OrderRepository;
import nl.lotrac.bv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderLineServiceImpl implements OrderLineService {

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;


    @Override

    public OrderLine createNewOrderLine(CreateOrderLine createOrderLine) {
        log.debug(createOrderLine.toString());

        Order order = orderRepository.getOrderByOrdername(createOrderLine.getOrderName());

        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        log.debug("!!! username:  " + username);


        String ordername1 = order.getOrdername();
        log.debug("!!! ordername: " + ordername1);


        if(!order.getUser().getUsername().equals(username))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

//    order heeft de velden:
//      private long id;
//      private String ordername;
//      private String status;
//
//     hier is dus een copy uit de repositoryOrder gemaakt mbv de createOrdeLine, CreateOrderLine createOrderLine
//
//        log.debug("ordername:" + order.getOrdername());

        if (orderRepository.getOrderByOrdername(order.getOrdername()) == null)
            throw new NameExistsException("order nok");
//
//
//        if (orderLineRepository.getOrderLineByItemname(createOrderLine.getItemName()) != null)
//            throw new NameExistsException("orderLine exists");

        log.debug("order Id: " + String.valueOf(order.getId()));
        log.debug("ordername: " + order.getOrdername());
        log.debug("orderStatus: " + order.getStatus());

        OrderLine newOrderLine = new OrderLine();


        newOrderLine.setItemname(createOrderLine.getItemName());
        newOrderLine.setQuantity(createOrderLine.getQuantity());
        log.debug("newOrderline ItemName: " + newOrderLine.getItemname());
        log.debug("newOrderline Quantity: " + newOrderLine.getQuantity());


//  wanneer je nu de order teruggeeft dmv de setter weetJPA via @ManyToOne dat hij dat in de foreignKeymoet zetten
        newOrderLine.setOrder(order);


// pas na save wordt een nieuwe id aangemaakt
        return orderLineRepository.save(newOrderLine);
    }


    @Override
    public List<OrderLine> getAllOrderLines() {

        return orderLineRepository.findAll();
    }

    @Override
    public OrderLine getOneOrderLineByID(Long id) {

        Optional<OrderLine> orderLine = orderLineRepository.findById(id);
        if (orderLine.isEmpty()) {
            throw new NameNotFoundException("orderLine does not exists");
        } else {
            return orderLine.get();
        }
    }


    //    In repository staat getOrderLineByItemName
    @Override
    public OrderLine getOneOrderLineByName(String itemname) {

        OrderLine orderLine = orderLineRepository.getOrderLineByItemname(itemname);
        if (orderLine == null)
            throw new NameNotFoundException("orderLine does not exists");

        return orderLine;
    }


}
