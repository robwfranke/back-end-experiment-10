package nl.lotrac.bv.controller;

import nl.lotrac.bv.model.Job;
import nl.lotrac.bv.model.MessageFrontEnd;
import nl.lotrac.bv.model.Order;
import nl.lotrac.bv.service.JobService;
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
    private JobService jobService;


    //********************************************************************************
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewJob(@RequestBody Job job) {

        jobService.createNewJob(job);


        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{create}")
                .buildAndExpand(job.getJobname()).toUri();

        return ResponseEntity.created(location).body(job);

    }


}