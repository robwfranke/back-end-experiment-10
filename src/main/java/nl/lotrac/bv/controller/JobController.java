package nl.lotrac.bv.controller;

import nl.lotrac.bv.model.MessageFrontEnd;
import nl.lotrac.bv.model.Order;
import nl.lotrac.bv.service.OrderLineService;
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
@RequestMapping(value = "/jobs")


public class JobController {

    @Autowired
    private OrderLineService orderLineService;




}