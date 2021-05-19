package nl.lotrac.bv.controller;

import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.controller.model.CreateOrderLine;
import nl.lotrac.bv.model.OrderLine;
import nl.lotrac.bv.repository.OrderRepository;
import nl.lotrac.bv.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/orderlines")


@Slf4j
public class OrderLineController {


    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private OrderLineService orderLineService;

    @PostMapping(value="/create")

    public ResponseEntity<OrderLine>createNewOrderLine(@RequestBody CreateOrderLine createOrderLine ){
        log.debug(createOrderLine.toString());




        OrderLine orderLine= orderLineService.createNewOrderLine(createOrderLine);


        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{create}")
                .buildAndExpand(orderLine.getItemname()).toUri();

        return ResponseEntity.created(location).body(orderLine);
    }





    @GetMapping(value = "")
    public ResponseEntity<Object> getAllOrderLines() {

        return ResponseEntity.ok().body(orderLineService.getAllOrderLines());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderLine>getOneOrderLineByID(@PathVariable("id")Long id){
        return new ResponseEntity<>(orderLineService.getOneOrderLineByID(id),HttpStatus.OK) ;
    }



//    In repository staat getOrderLineByKoekoek

    @GetMapping(value = "/name/{ordername}")
    public ResponseEntity<Object> getOneOrderLineByName(@PathVariable("ordername") String itemname) {
        return ResponseEntity.ok().body(orderLineService.getOneOrderLineByName(itemname));
    }





}
