package nl.lotrac.bv.service;


import nl.lotrac.bv.exceptions.NameExistsException;
import nl.lotrac.bv.exceptions.NameNotFoundException;
import nl.lotrac.bv.model.Order;
import nl.lotrac.bv.model.OrderLine;
import nl.lotrac.bv.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class OrderLineServiceImpl implements OrderLineService{

    @Autowired
    private OrderLineRepository orderLineRepository;


    @Override
    public String createNewOrderLine(OrderLine orderLine) {

        if (orderLineRepository.getOrderLineByItemname(orderLine.getItemname()) != null)
            throw new NameExistsException("orderLine exists");

        OrderLine newOrderLine = orderLineRepository.save(orderLine);
        return (newOrderLine.getItemname());
    }










    @Override
    public List<OrderLine> getAllOrderLines(){

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

        OrderLine orderLine=orderLineRepository.getOrderLineByItemname(itemname);
        if (orderLine == null)
            throw new NameNotFoundException("orderLine does not exists");

        return orderLine;
    }




}
